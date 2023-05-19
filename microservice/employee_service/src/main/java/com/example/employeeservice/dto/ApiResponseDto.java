package com.example.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {

    @Schema(description = "Employee DTO")
    private EmployeeDto employee;
    @Schema(description = "Department DTO")
    private DepartmentDto department;
    @Schema(description = "Organization DTO")
    private OrganizationDto organization;
}
