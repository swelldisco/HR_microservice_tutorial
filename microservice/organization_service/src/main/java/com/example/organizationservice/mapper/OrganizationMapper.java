package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;

public class OrganizationMapper {
    
    public static Organization mapToOrganization(OrganizationDto source) {
        return new Organization(
            source.getId(),
            source.getOrganizationName(),
            source.getOrganizationDescription(),
            source.getOrganizationCode(),
            source.getCreationDate()
            );
    }

    public static OrganizationDto mapToDto(Organization source) {
        return new OrganizationDto(
            source.getId(),
            source.getOrganizationName(),
            source.getOrganizationDescription(),
            source.getOrganizationCode(),
            source.getCreationDate()
            );
    }

}
