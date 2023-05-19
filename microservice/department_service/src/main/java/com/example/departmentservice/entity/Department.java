package com.example.departmentservice.entity;

import com.example.departmentservice.dto.DepartmentDto;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "department_description")
    private String departmentDescription;

    @Column(name = "department_code", nullable = false, unique = true)
    private String departmentCode;

    // DepartmentDto to Department mapper
    // A mapper class is likely a better choice since it's all in one place
    // and won't come with the CVEs that mapper libraries come with
    public Department(DepartmentDto source) {
        this.id = source.getId();
        this.departmentName = source.getDepartmentName();
        this.departmentDescription = source.getDepartmentDescription();
        this.departmentCode = source.getDepartmentCode();
    }
    
}
