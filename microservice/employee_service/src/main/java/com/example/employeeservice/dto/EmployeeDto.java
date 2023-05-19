package com.example.employeeservice.dto;

import com.example.employeeservice.entity.Employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    
    @Schema(description = "Employee id number")
    private Long id;

    @Schema(description = "Employee first name")
    @NonNull
    @NotBlank(message = "First name cannot be left blank.")
    @NotEmpty(message = "First name cannot be left empty.")
    private String firstName;

    @Schema(description = "Employee last name")
    @NonNull
    @NotBlank(message = "Last name cannot be left blank.")
    @NotEmpty(message = "Last name cannot be left empty.")
    private String lastName;

    @Schema(description = "Employee email address")
    @NonNull
    @NotBlank(message = "Email cannot be left blank.")
    @NotEmpty(message = "Email cannot be left empty.")
    @Email(message = "Please choose a valid email address.")
    private String email;

    @Schema(description = "Employee department code")
    private String departmentCode;

    @Schema(description = "Employee organizatio code")
    private String organizationCode;

    // Employee to EmployeeDto mapper
    public EmployeeDto(Employee source) {
        this.id = source.getId();
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
        this.email = source.getEmail();
        this.departmentCode = source.getDepartmentCode();
        this.organizationCode = source.getOrganizationCode();
    }

    // constructor for feeding the database test employees
    // public EmployeeDto(String firstName, String lastName, String email) {
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.email = email;
    // }

    public EmployeeDto(String firstName, String lastName, String email, String departmentCode, String organizationCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentCode = departmentCode;
        this.organizationCode = organizationCode;
    }
    
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getEmail() + " " + getDepartmentCode();
    }

}
