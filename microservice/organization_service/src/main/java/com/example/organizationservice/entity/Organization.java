package com.example.organizationservice.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.organizationservice.dto.OrganizationDto;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name", nullable = false, unique = true)
    private String organizationName;

    @Column(name = "description")
    private String organizationDescription;

    @Column(name = "organization_code", nullable = false, unique = true)
    private String organizationCode;

    @Column(name = "creation_date")
    @CreationTimestamp
    @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    // mapper constructor just in case
    public Organization (OrganizationDto source) {
            this.id = source.getId();
            this.organizationName = source.getOrganizationName();
            this.organizationDescription = source.getOrganizationDescription();
            this.organizationCode = source.getOrganizationCode();
            this.creationDate = source.getCreationDate();
    }

    @Override
    public String toString() {
        return "Organization id: " + getId() + "\nOrganization name: " + getOrganizationName() + "\nDescription: " + getOrganizationDescription() + "\nCode: " + getOrganizationCode() + "\nCreation date: " + getCreationDate();
    }
}
