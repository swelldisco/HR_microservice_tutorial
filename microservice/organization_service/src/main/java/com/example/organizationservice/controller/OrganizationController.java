package com.example.organizationservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Organization Service Controller",
description = "Create, retrieve, update, and delete organizations.")
@RestController
@AllArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {
    
    private OrganizationService organizationService;

    // Create new organization by passing a valid DTO.
    // http://127.0.0.1:8083/api/organizations
    @Operation(
        summary = "REST API to create a new organization.",
        description = "Creates a new organization based on a validated organization dto object.")
    @ApiResponse(
        responseCode = "201",
        description = "HTTP STATUS 201 CREATED")
    @PostMapping()
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Valid OrganizationDto org) {
        return new ResponseEntity<>(organizationService.createOrganization(org), HttpStatus.CREATED);
    }

    // Get Organization by its organization code.
    // http://127.0.0.1:8083/api/organizations/code?organizationCode=TEST123
    @Operation(
        summary = "REST API to retrieve an organization.",
        description = "Retrieves an organization based on its code as a query.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/code")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@RequestParam String organizationCode) {
        return new ResponseEntity<>(organizationService.getOrganizationByCode(organizationCode), HttpStatus.OK);
    }

    // Get Organization by its name.
    // http://127.0.0.1:8083/api/organizations/name?organizationName=EUROPE
    @Operation(
        summary = "REST API to retrieve an organization.",
        description = "Retrieves an organization based on its name as a query.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/name")
    public ResponseEntity<OrganizationDto> getOrganizationByName(@RequestParam String organizationName) {
        return new ResponseEntity<>(organizationService.getOrganizationByName(organizationName), HttpStatus.OK);
    }

    // Get All organizations in the repository.
    // http://127.0.0.1:8083/api/organizations
    @Operation(
        summary = "REST API to retrieve all organizations.",
        description = "Retrieves all organizations in the repository.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping()
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        return new ResponseEntity<>(organizationService.getAllOrganizations(), HttpStatus.OK);
    }

    // Update an organization by its code and passing a valid DTO.
    // http://127.0.0.1:8083/api/organizations/TEST123
    @Operation(
        summary = "REST API to update an organization.",
        description = "Updates an organization based on a its code and a validated organization dto object.")
    @ApiResponse(
        responseCode = "201",
        description = "HTTP STATUS 202 ACCEPTED")
    @PutMapping("/{code}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable String code, @RequestBody @Valid OrganizationDto org) {
        return new ResponseEntity<>(organizationService.updateOrganization(code, org), HttpStatus.ACCEPTED);
    }

    // Delete an organization by it code.
    // http://127.0.0.1:8083/api/organizations/TEST123
    @Operation(
        summary = "REST API to delete an organization.",
        description = "Deletes an organization based on its code.")
    @ApiResponse(
        responseCode = "204",
        description = "HTTP STATUS 204 NO CONTENT")
    @DeleteMapping("/{code}")
    public ResponseEntity<HttpStatus> deleteOrganizationByCode(@PathVariable String code) {
        organizationService.deleteOrganizationByCode(code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
