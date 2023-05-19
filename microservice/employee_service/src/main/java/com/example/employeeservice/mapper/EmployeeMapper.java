package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;

public class EmployeeMapper {
    public static Employee mapToEmployee(EmployeeDto source) {
        return new Employee(
            source.getId(), 
            source.getFirstName(), 
            source.getLastName(), 
            source.getEmail(), 
            source.getDepartmentCode(),
            source.getOrganizationCode()
            );
    }

    public static EmployeeDto mapToDto(Employee source) {
        return new EmployeeDto(
            source.getId(), 
            source.getFirstName(), 
            source.getLastName(), 
            source.getEmail(), 
            source.getDepartmentCode(),
            source.getOrganizationCode()
            );
    }
}
