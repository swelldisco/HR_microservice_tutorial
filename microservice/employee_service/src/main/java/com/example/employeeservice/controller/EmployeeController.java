package com.example.employeeservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(
    name = "Employee Service Controller", 
    description = "Create, retrieve, update, and delete employee objects.")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // create a new employee
    // http://127.0.0.1:8081/api/employees
    @Operation(
        summary = "REST API to create a new employee.",
        description = "Creates a new employee based on a validated employee dto object.")
    @ApiResponse(
        responseCode = "201",
        description = "HTTP STATUS 201 CREATED")
    @PostMapping()
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    // get an employee by id
    // http://127.0.0.1:8081/api/employees/1
    @Operation(
        summary = "REST API to retrieve an employee.",
        description = "Retrieves an employee based on its id number.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    // get an employee by name.  Returns a list as you may have multiple employees with the same name
    // http://127.0.0.1:8081/api/employees/name?firstName=Billy&lastName=Bob
    @Operation(
        summary = "REST API to retrieve an employee.",
        description = "Retrieves a list of employees based on first name and last name.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/name")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByName(@RequestParam String firstName, @RequestParam String lastName) {
        return new ResponseEntity<>(employeeService.getEmployeeByName(firstName, lastName), HttpStatus.OK);
    }

    // get an employee by email
    // http://127.0.0.1:8081/api/employees/email?email=BillyBob@gmail.com
    @Operation(
        summary = "REST API to retrieve an employee.",
        description = "Retrieves an employee based on email address.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/email")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@RequestParam String email) {
        return new ResponseEntity<>(employeeService.getEmployeeByEmail(email), HttpStatus.OK);
    }

    // get all employees in the repository
    // http://127.0.0.1:8081/api/employees
    @Operation(
        summary = "REST API to retrieve all employees.",
        description = "Retrieves a list of all employees in the repository.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    // get all employees in a department
    // http://127.0.0.1:8081/api/employees/department/TEST
    @Operation(
        summary = "REST API to retrieve a list employees.",
        description = "Retrieves a list of all employees in a department.")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/department/{code}")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesByDepartment(@PathVariable("code") String departmentCode) {
        return new ResponseEntity<>(employeeService.getAllByDepartment(departmentCode), HttpStatus.OK);
    }

    // update an employee by id
    // http://127.0.0.1:8081/api/employees/1
    @Operation(
        summary = "REST API to update an employee.",
        description = "Updates an employee based on its id number and a valid employee dto.")
    @ApiResponse(
        responseCode = "202",
        description = "HTTP STATUS 202 ACCEPTED")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDto), HttpStatus.ACCEPTED);
    }

    // delete an employee by id
    // http://127.0.0.1:8081/api/employees/1
    @Operation(
        summary = "REST API to delete an employee.",
        description = "Deletes an employee based on its id number.")
    @ApiResponse(
        responseCode = "204",
        description = "HTTP STATUS 204 NO CONTENT")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
