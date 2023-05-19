package com.example.organizationservice.service;

import java.util.List;

import com.example.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    
    OrganizationDto createOrganization(OrganizationDto org);
    OrganizationDto getOrganizationByCode(String code);
    OrganizationDto getOrganizationByName(String name);
    List<OrganizationDto> getAllOrganizations();
    OrganizationDto updateOrganization(String code, OrganizationDto org);
    void deleteOrganizationByCode(String code);

}
