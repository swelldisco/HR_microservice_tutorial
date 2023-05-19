package com.example.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employeeservice.dto.DepartmentDto;

@FeignClient("DEPARTMENT-SERVICE")
public interface APIClient {
    // get a department by its department code as a path variable
    // http://127.0.0.1:8080/api/departments/code?departmentCode=SALES
    @GetMapping("/api/departments/code")
    DepartmentDto getDepartmentByCode(@RequestParam String departmentCode);
}
