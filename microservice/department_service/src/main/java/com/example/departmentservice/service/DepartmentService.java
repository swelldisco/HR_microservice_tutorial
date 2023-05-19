package com.example.departmentservice.service;

import java.util.List;

import com.example.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
    DepartmentDto getDepartmentByName(String departmentName);
    DepartmentDto getDepartmentByCode(String departmentCode);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartment(String departmentCode, DepartmentDto tempDepartment);
    void deleteDepartment(String departmentCode);
}
