package com.example.organizationservice.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.exception.OrganizationNotFoundException;
import com.example.organizationservice.exception.RepositoryEmptyException;
import com.example.organizationservice.mapper.OrganizationMapper;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.service.OrganizationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto createOrganization(OrganizationDto org) {
        Organization newOrg = OrganizationMapper.mapToOrganization(org);
        organizationRepository.save(newOrg);
        return OrganizationMapper.mapToDto(newOrg);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        return OrganizationMapper.mapToDto(pokeOrganizationByCode(code));
    }

    @Override
    public OrganizationDto getOrganizationByName(String name) {
        return OrganizationMapper.mapToDto(pokeOrganizationByName(name));
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        if (!organizationRepository.findAll().isEmpty() && organizationRepository.findAll() != null) {
            return organizationRepository.findAll().stream()
                .map(o -> OrganizationMapper.mapToDto(o))
                .toList();
        } else {
            throw new RepositoryEmptyException();
        }
    }

    @Override
    public OrganizationDto updateOrganization(String code, OrganizationDto org) {
        Organization updatedOrg = pokeOrganizationByCode(code);
        updatedOrg.setOrganizationName(org.getOrganizationName());
        updatedOrg.setOrganizationDescription(org.getOrganizationDescription());
        updatedOrg.setOrganizationCode(org.getOrganizationCode());
        updatedOrg.setCreationDate(org.getCreationDate());
        organizationRepository.save(updatedOrg);
        return OrganizationMapper.mapToDto(updatedOrg);
    }

    @Override
    public void deleteOrganizationByCode(String code) {
        if (organizationRepository.existsByOrganizationCodeIgnoreCase(code)) {
            organizationRepository.deleteByOrganizationCodeIgnoreCase(code);
        } else {
            throw new OrganizationNotFoundException("Organization", "Code", code);
        }
    }

    // More shaking and poking optionals before passing them to the calling method
    protected Organization pokeOrganizationById(Long id) {
        return organizationRepository.findById(id)
            .orElseThrow(() -> new OrganizationNotFoundException("Organization", "Id", id.toString()));
    }

    private Organization pokeOrganizationByName(String name) {
        return organizationRepository.findByOrganizationNameIgnoreCase(name)
            .orElseThrow(() -> new OrganizationNotFoundException("Organization", "Name", name));
    }
    
    private Organization pokeOrganizationByCode(String code) {
        return organizationRepository.findByOrganizationCodeIgnoreCase(code)
            .orElseThrow(() -> new OrganizationNotFoundException("Organization", "Code", code));
    }

    // thought I might need this, but maybe not
    protected Long findIdByCode(String code) {
        return pokeOrganizationByCode(code).getId();
    }
    
}
