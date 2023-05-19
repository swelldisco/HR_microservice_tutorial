package com.example.departmentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.departmentservice.entity.Department;

import jakarta.transaction.Transactional;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);
    Optional<Department> findByDepartmentCodeIgnoreCase(String departmentCode);
    boolean existsByDepartmentCodeIgnoreCase(String departmentCode);

    @Transactional
    void deleteByDepartmentCodeIgnoreCase(String departmentCode);
}
