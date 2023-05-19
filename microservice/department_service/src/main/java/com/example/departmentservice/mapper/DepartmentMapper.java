package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;

public class DepartmentMapper {
    public static Department mapToDepartment(DepartmentDto source) {
        return new Department(
            source.getId(), 
            source.getDepartmentName(), 
            source.getDepartmentDescription(), 
            source.getDepartmentCode()
            );
    }

    public static DepartmentDto mapToDto(Department source) {
        return new DepartmentDto(
            source.getId(), 
            source.getDepartmentName(), 
            source.getDepartmentDescription(), 
            source.getDepartmentCode()
            );
    }
}
