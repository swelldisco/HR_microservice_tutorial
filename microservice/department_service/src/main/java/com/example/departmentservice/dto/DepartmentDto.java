package com.example.departmentservice.dto;

import com.example.departmentservice.entity.Department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    
    @Schema(description = "Department id number")
    private Long id;

    @Schema(description = "Department name")
    @NonNull
    @NotBlank(message = "Department name cannot be left blank.")
    @NotEmpty(message = "Department name cannot be left empty.")
    private String departmentName;

    @Schema(description = "Department description")
    private String departmentDescription;

    @Schema(description = "Department code")
    @NonNull
    @NotBlank(message = "Department code cannot be left blank.")
    @NotEmpty(message = "Department code cannot be left empty.")
    private String departmentCode;

    // Department to DepartmentDto mapper
    public DepartmentDto(Department source) {
        this.id = source.getId();
        this.departmentName = source.getDepartmentName();
        this.departmentDescription = source.getDepartmentDescription();
        this.departmentCode = source.getDepartmentCode();
    }

    // test constructor to stuff departments into the database
    public DepartmentDto(String departmentName, String departmentDescription, String departmentCode) {
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.departmentCode = departmentCode;
    }
    
}
