package com.example.organizationservice;

//import java.util.Arrays;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

//import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
	info = @Info(
		title = "Organization Service REST APIs",
		description = "Organization Service REST API documentation",
		version = "v0.0.0.0",
		contact = @Contact(
			name = "New API, who dis",
			email = "no.contact@no_email.com",
			url = "http://github.com/swelldisco"
		),
		license = @License(
			name = "This is pretty much all from Ramesh's Javaguides.net course, so it's all educational use only.  Does that have an actual license name?",
			url = "http://javaguides.net"

		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Organization Service Doc",
		url = "http://no.idea.here")		
)

@SpringBootApplication
public class OrganizationServiceApplication implements CommandLineRunner {

	@Lazy
	@Autowired
	private OrganizationService organizationService;

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Use this to dump organizations into the database for testing
		// List<OrganizationDto> testDtos = Arrays.asList(
		// 	new OrganizationDto("Americas", "North and South American Branches","ORG1"),
		// 	new OrganizationDto("Europe", "European and North African Branches", "ORG2"),
		// 	new OrganizationDto("South-East Asia", "South-East Asian Branches", "ORG3"),
		// 	new OrganizationDto("Australian and Oceana", "Australian and Oceana Branches", "ORG4")
		// );

		// for (OrganizationDto dto : testDtos) {
		// 	organizationService.createOrganization(dto);
		// }
		// OrganizationDto adto = new OrganizationDto("East Asian", "East Asian Branches", "ORGCODE");
		// System.out.println(adto);
		// organizationService.createOrganization(adto);
	}

}
