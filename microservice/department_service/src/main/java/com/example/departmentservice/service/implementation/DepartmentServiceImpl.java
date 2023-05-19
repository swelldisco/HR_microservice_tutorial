package com.example.departmentservice.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.exception.DepartmentNotFoundException;
import com.example.departmentservice.exception.RepositoryEmptyException;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department newDepartment = new Department(departmentDto);
        departmentRepository.save(newDepartment);
        return new DepartmentDto(newDepartment);
    }
    
    @Override
    public DepartmentDto getDepartmentById(Long id) {
        return new DepartmentDto(pokeDepartmentById(id));
    }

    public DepartmentDto getDepartmentByName(String departmentName) {
        return new DepartmentDto(pokeDepartmentByName(departmentName));
    }

    public DepartmentDto getDepartmentByCode(String departmentCode) {
        return new DepartmentDto(pokeDepartmentByCode(departmentCode));
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        if (!departmentRepository.findAll().isEmpty() && departmentRepository.findAll() != null) {
            return departmentRepository.findAll().stream()
                .map(d -> new DepartmentDto(d))
                .toList();
        } else {
            throw new RepositoryEmptyException();
        }
    }

    @Override
    public DepartmentDto updateDepartment(String departmentCode, DepartmentDto tempDepartment) {
        Department department = pokeDepartmentByCode(departmentCode);
        department.setDepartmentName(tempDepartment.getDepartmentName());
        department.setDepartmentDescription(tempDepartment.getDepartmentDescription());
        department.setDepartmentCode(tempDepartment.getDepartmentCode());
        departmentRepository.save(department);
        return new DepartmentDto(department);
    }

    @Override
    public void deleteDepartment(String departmentCode) {
        if (departmentRepository.existsByDepartmentCodeIgnoreCase(departmentCode)) {
            departmentRepository.deleteByDepartmentCodeIgnoreCase(departmentCode);
        } else {
            throw new DepartmentNotFoundException("Department", "Department Code", departmentCode);
        }
    }

    // violently shake Optionals to make sure there's a Department inside before returning them to the calling method
    private Department pokeDepartmentById(Long id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new DepartmentNotFoundException("Department", "Department Id", id.toString()));
    }

    private Department pokeDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName)
            .orElseThrow(() -> new DepartmentNotFoundException("Department", "Department Name", departmentName));
    }

    private Department pokeDepartmentByCode(String departmentCode) {
        return departmentRepository.findByDepartmentCodeIgnoreCase(departmentCode)
            .orElseThrow(() -> new DepartmentNotFoundException("Department", "Department Code", departmentCode));
    }
}
