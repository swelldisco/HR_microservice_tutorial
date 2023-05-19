package com.example.employeeservice.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.OrganizationDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.exception.RepositoryEmptyException;
import com.example.employeeservice.repository.EmployeeRepository;
// import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;

// import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    // private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;
    // private APIClient apiClient;
    private WebClient webClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = new Employee(employeeDto);
        employeeRepository.save(newEmployee);
        return new EmployeeDto(newEmployee);
    }

    // @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        // LOGGER.info("inside getEmployeeById() method");
        EmployeeDto employeeDto = new EmployeeDto(pokeEmployeeById(id));
        // DepartmentDto departmentDto = apiClient.getDepartmentByCode(employeeDto.getDepartmentCode());

        DepartmentDto departmentDto = webClient.get()
            .uri("http://127.0.0.1:8080/api/departments/code?departmentCode=" + employeeDto.getDepartmentCode())
            .retrieve()
            .bodyToMono(DepartmentDto.class)
            .block();

        OrganizationDto organizationDto = webClient.get()
            .uri("http://127.0.0.1:8083/api/organizations/code?organizationCode=" + employeeDto.getOrganizationCode())
            .retrieve()
            .bodyToMono(OrganizationDto.class)
            .block();

        return new ApiResponseDto(employeeDto, departmentDto, organizationDto);
    }

    public ApiResponseDto getDefaultDepartment(Long id, Exception ex) {
        // LOGGER.info("inside getDefaultDepartment() method");
        EmployeeDto employeeDto = new EmployeeDto(pokeEmployeeById(id));
        DepartmentDto departmentDto = new DepartmentDto(
            0L,
            "Default Department Name", 
            "A default department object to return when the Department Service is down", 
            "DEPT");

        OrganizationDto organizationDto = new OrganizationDto(
            0L,
            "Default Organization Name",
            "A default organization object to return when the Organization Service is down",
            "NO_ORG_FOUND",
            LocalDateTime.now()
        );
        return new ApiResponseDto(employeeDto, departmentDto, organizationDto);
    }

    @Override
    public List<EmployeeDto> getEmployeeByName(String firstName, String lastName) {
        if (!employeeRepository.findAllByFirstNameAndLastNameIgnoreCase(firstName, lastName).isEmpty() && employeeRepository.findAllByFirstNameAndLastNameIgnoreCase(firstName, lastName) != null) {
            return employeeRepository.findAllByFirstNameAndLastNameIgnoreCase(firstName, lastName).stream()
                .map(e -> new EmployeeDto(e))
                .toList();
        } else {
            throw new EmployeeNotFoundException("Employee", "Name", firstName + " " + lastName);
        }
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        return new EmployeeDto(pokeEmployeeByEmail(email));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        if (!employeeRepository.findAll().isEmpty() && employeeRepository.findAll() != null) {
            return employeeRepository.findAll().stream()
                .map(e -> new EmployeeDto(e))
                .toList();
        } else {
            throw new RepositoryEmptyException();
        }
    }

    @Override
    public List<EmployeeDto> getAllByDepartment(String departmentCode) {
        if (!employeeRepository.findAllByDepartmentCodeIgnoreCase(departmentCode).isEmpty() && employeeRepository.findAllByDepartmentCodeIgnoreCase(departmentCode) != null) {
            return employeeRepository.findAllByDepartmentCodeIgnoreCase(departmentCode).stream()
                .map(e -> new EmployeeDto(e))
                .toList();
        } else {
            throw new EmployeeNotFoundException("Employee", "Department Code", departmentCode);
        }
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = pokeEmployeeById(id);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        employee.setOrganizationCode(employeeDto.getOrganizationCode());
        employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException("Employee", "Employee Id", id.toString());
        }
    }

    // violently shake Optionals to make sure there's an Employee inside before returning them to the calling method
    private Employee pokeEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee", "Employee Id", id.toString()));
    }

    private Employee pokeEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee", "Employee Email", email));
    }
    
}
