package com.example.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    
    @Schema(description = "Department id")
    private Long id;
    @Schema(description = "Department name")
    private String departmentName;
    @Schema(description = "Department description")
    private String departmentDescription;
    @Schema(description = "Department code")
    private String departmentCode;

    @Override
    public String toString() {
        return getDepartmentName() + " " + getDepartmentDescription() + " " + getDepartmentCode();
    }
}
