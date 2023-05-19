package com.example.organizationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organizationservice.entity.Organization;

import jakarta.transaction.Transactional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    
    boolean existsByOrganizationCodeIgnoreCase(String organizationCode);
    Optional<Organization> findByOrganizationCodeIgnoreCase(String organizationCode);
    Optional<Organization> findByOrganizationNameIgnoreCase(String organizationName);

    @Transactional
    void deleteByOrganizationCodeIgnoreCase(String organizationCode);

}
