package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.EmployeeJpaProjectApplication;
import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MainController {
    static Logger logger = Logger.getLogger(MainController.class.getName());

    //services
    private EmployeeRepository employeeRepository;
    private EmployeesService employeesService;
    private DepartmentsService departmentsService;
    private SalariesService salariesService;
    private ApiKeyService apiKeyService;

    @Autowired
    public MainController(ApiKeyService apiKeyService, EmployeeRepository employeeRepository, EmployeesService employeesService, DepartmentsService departmentsService, SalariesService salariesService) {
        this.apiKeyService = apiKeyService;
        this.employeeRepository = employeeRepository;
        this.employeesService = employeesService;
        this.departmentsService = departmentsService;
        this.salariesService = salariesService;
    }

    @GetMapping("/api/generate/{id}")
    public String generateApiKey(@PathVariable Integer id) {
        return apiKeyService.generateApiKey(id);
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployeeById(@RequestParam int id, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        Optional<EmployeeDTO> employeeDTOOptional = employeeRepository.findById(id);
        if (employeeDTOOptional.isPresent()) {
            logger.log(Level.INFO, "Employee " + id + " found: " + employeeDTOOptional.get());
            return new ResponseEntity<EmployeeDTO>(employeeDTOOptional.get(), HttpStatus.OK);
        } else {
            logger.log(Level.INFO, "Employee " + id + " not found");
            return new ResponseEntity<>("Employee " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/lastName")
    public ResponseEntity<?> getEmployeesByLastName(@RequestParam String lastName, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        Optional<List<EmployeeDTO>> list = employeesService.getEmployeesByLastName(lastName);
        if (list.isPresent()) {
            return new ResponseEntity<>(list.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee with last name: " + lastName + ", not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/salary/range") // example = /salary/range?jobTitle=Senior+Engineer&year=1986
    public HttpEntity<?> getLowestAndHighestSalaryForJobTitleDuringAYear(@RequestParam String jobTitle, @RequestParam int year, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        Optional<Map<String, BigDecimal>> map = salariesService.getLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);
        if (map.isPresent()) {
            return new ResponseEntity<>(map.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for job title: " + jobTitle + ", year: " + year, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/salary/genderPayGap") // example = /salary/genderPayGap?fromYear=1980&toYear=2000
    public HttpEntity<?> getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(@RequestParam int fromYear, @RequestParam int toYear, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        Optional<List<Object[]>> list = salariesService.getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);
        if (list.isPresent()) {
            return new ResponseEntity<>(list.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for the percentage gender pay gap between years: " + fromYear + " and " + toYear, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/salary/department/average") // example = /salary/department/average?department=Finance&date=1988-10-23
    public HttpEntity<?> getAverageSalaryForDepartmentOnGivenDate(@RequestParam String department, @RequestParam LocalDate date, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        Optional<Map<String, BigDecimal>> map = salariesService.getAverageSalaryForDepartmentOnGivenDate(department, date);
        if (map.isPresent()) {
            return new ResponseEntity<>(map.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for department: " + department + ", date: " + date, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/salary/progression") // example = /salary/progression?empNo=10001
    public HttpEntity<?> getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(@RequestParam int empNo, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        Optional<List<Integer>> list = salariesService.getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);
        if (list.isPresent()) {
            return new ResponseEntity<>(list.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for employee number: " + empNo, HttpStatus.NOT_FOUND);
        }
    }
}
