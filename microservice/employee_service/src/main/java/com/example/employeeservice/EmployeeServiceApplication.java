package com.example.employeeservice;

//import java.util.Arrays;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.function.client.WebClient;

//import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
	info = @Info(
		title = "Employee Service REST APIs",
		description = "Employee Service REST API documentation",
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
		description = "Employee Service Doc",
		url = "http://no.idea.here")		
)

@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication implements CommandLineRunner {

	// in order to have the spring bean and the employee service you must:
	// make a proper constructor for the bean (not all args constructors..)
	// annotate the service class with lazy
	// annotate the service class with autowired (all args constructor will not work, and you will get an exception when you try to run the application)
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	@Lazy
	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ** use this to add employees to the database **
		// List<EmployeeDto> testEmployees = Arrays.asList(
		// 	new EmployeeDto("Billy", "Bob", "BillyBob@gmail.com", "IT", "ORG1"),
		// 	new EmployeeDto("Jim", "Bob", "JB@UVA.edu", "SALES", "ORG2"),
		// 	new EmployeeDto("Jasper", "Jones", "JJ@mail.ru", "SALES", "ORG1"),
		// 	new EmployeeDto("Bubba", "Beauregard", "BubsJr@fakemail.com","DATA_SCI", "ORG4"),
		// 	new EmployeeDto("Jethro", "McGimpy", "NoTullJethro@gmail.com", "ENG", "ORG2"),
		// 	new EmployeeDto("Otis", "Wright", "TheWrightOtis@gmail.com", "HR", "ORG1")
		// );

		// for (EmployeeDto employee : testEmployees) {
		// 	employeeService.createEmployee(employee);
		// }
	}

}
