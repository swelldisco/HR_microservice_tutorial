package com.example.organizationservice.dto;

import java.time.LocalDateTime;

import com.example.organizationservice.entity.Organization;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {

    @Schema(description = "Organization id number")
    private Long id;

    @Schema(description = "Organization name")
    @NonNull
    @NotBlank(message = "Organization name cannot be left blank.")
    @NotEmpty(message = "Organization name cannot be left empty.")
    private String organizationName;
    
    @Schema(description = "Organization description")
    private String organizationDescription;

    @Schema(description = "Organization code")
    @NonNull
    @NotBlank(message = "Organization name cannot be left blank.")
    @NotEmpty(message = "Organization name cannot be left empty.")
    private String organizationCode;

    @Schema(description = "Organization creation date")
    @PastOrPresent
    @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    // mapper
    public OrganizationDto (Organization source) {
        this.id = source.getId();
        this.organizationName = source.getOrganizationName();
        this.organizationDescription = source.getOrganizationDescription();
        this.organizationCode = source.getOrganizationCode();
        this.creationDate = source.getCreationDate();
}

    // constructor for feeding in test organizations
    public OrganizationDto(String organizationName, String organizationDescription, String organizationCode) {
        this.organizationName = organizationName;
        this.organizationDescription = organizationDescription;
        this.organizationCode = organizationCode;
    }

    @Override
    public String toString() {
        return "Organization id: " + getId() + "\nOrganization name: " + getOrganizationName() + "\nDescription: " + getOrganizationDescription() + "\nCode: " + getOrganizationCode() + "\nCreation date: " + getCreationDate();
    }

}
