package com.example.employeeservice.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    
    @Schema(description = "Organization id number")
    private Long id;
    @Schema(description = "Organization name")
    private String organizationName;
    @Schema(description = "Organization description")
    private String organizationDescription;
    @Schema(description = "Organization code")
    private String organizationCode;
    @Schema(description = "Organization creation date")
    @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

}
