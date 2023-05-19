package com.example.employeeservice.entity;

import com.example.employeeservice.dto.EmployeeDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "department_code")
    private String departmentCode;

    @Column(name = "organization_code")
    private String organizationCode;

    // EmployeeDto to Employee mapper
    public Employee(EmployeeDto source) {
        this.id = source.getId();
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
        this.email = source.getEmail();
        this.departmentCode = source.getDepartmentCode();
        this.organizationCode = source.getOrganizationCode();
    }

}
