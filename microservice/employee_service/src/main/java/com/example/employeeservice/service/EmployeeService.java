package com.example.employeeservice.service;

import java.util.List;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    ApiResponseDto getEmployeeById(Long id);
    List<EmployeeDto> getEmployeeByName(String firstName, String lastName);
    EmployeeDto getEmployeeByEmail(String email);
    List<EmployeeDto> getAllEmployees();
    List<EmployeeDto> getAllByDepartment(String departmentCode);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);

}
