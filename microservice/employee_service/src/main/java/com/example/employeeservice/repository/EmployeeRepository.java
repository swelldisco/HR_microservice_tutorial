package com.example.employeeservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    Optional<Employee> findByEmail(String email);
    List<Employee> findAllByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);
    List<Employee> findAllByDepartmentCodeIgnoreCase(String departmentCode);
    
}
