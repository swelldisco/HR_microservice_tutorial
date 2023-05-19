package com.example.departmentservice;

//import java.util.Arrays;
//import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

//import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.AllArgsConstructor;

@OpenAPIDefinition(
	info = @Info(
		title = "Department Service REST APIs",
		description = "Department Service REST API documentation",
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
		description = "Department Service Doc",
		url = "http://no.idea.here")		
)

@SpringBootApplication
@AllArgsConstructor
public class DepartmentServiceApplication implements CommandLineRunner {

	@Lazy
	private DepartmentService departmentService;

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// auto generate department DTOs for the database here
		// List<DepartmentDto> testDepartments = Arrays.asList(
		// 	new DepartmentDto("IT", "Information Technology", "IT"),
		// 	new DepartmentDto("Sales", "Sales and Marketing", "SALES"),
		// 	new DepartmentDto("HR", "Human Resources", "HR"),
		// 	new DepartmentDto("Engineering", "Product development", "ENG"),
		// 	new DepartmentDto("Data Science", "Market research, development, and product discovery", "DATA_SCI")
		// );

		// for (DepartmentDto department : testDepartments) {
		// 	departmentService.createDepartment(department);
		// }
	}

}
