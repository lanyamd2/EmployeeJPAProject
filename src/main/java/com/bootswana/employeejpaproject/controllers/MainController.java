package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.exception.ClientNotAuthorisedException;
import com.bootswana.employeejpaproject.model.dtos.DepartmentDTO;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import com.bootswana.employeejpaproject.service.*;
import com.bootswana.employeejpaproject.model.dtos.SalaryDTO;
import com.bootswana.employeejpaproject.model.dtos.SalaryDTOId;
import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import com.bootswana.employeejpaproject.model.repositories.SalaryRepository;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class MainController {
    static Logger logger = Logger.getLogger(MainController.class.getName());

    //services
    private ApiKeyService apiKeyService;
    private EmployeeRepository employeeRepository;
    private EmployeesService employeesService;
    private DepartmentRepository departmentRepository;
    private DepartmentsService departmentsService;
    private SalaryRepository salaryRepository;
    private SalariesService salariesService;

    @Autowired
    public MainController(ApiKeyService apiKeyService, EmployeeRepository employeeRepository, EmployeesService employeesService, DepartmentRepository departmentRepository, DepartmentsService departmentsService, SalaryRepository salaryRepository, SalariesService salariesService) {
        this.apiKeyService = apiKeyService;
        this.employeeRepository = employeeRepository;
        this.employeesService = employeesService;
        this.departmentRepository = departmentRepository;
        this.departmentsService = departmentsService;
        this.salaryRepository = salaryRepository;
        this.salariesService = salariesService;
    }

    @GetMapping("/api/generate/{accessLevel}")
    public ResponseEntity<?> generateApiKey(@PathVariable Integer accessLevel) {
        if (accessLevel == 1 || accessLevel == 2 || accessLevel == 3) {
            String message = apiKeyService.generateApiKey(accessLevel);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            logger.log(Level.WARNING, "The client has not entered a correct API access level");
            return new ResponseEntity<>("Incorrect API access level", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/check/{apiKey}")
    public ResponseEntity<?> checkApiKey(@PathVariable String apiKey) throws ApiKeyNotFoundException {
        int accessLevel = apiKeyService.getAccessLevel(apiKey);

        return new ResponseEntity<>("Key: " + apiKey + " has level " + accessLevel + " access rights", HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployeeById(@RequestParam int id, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);

        Optional<EmployeeDTO> employeeDTOOptional = employeeRepository.findById(id);
        if (employeeDTOOptional.isPresent()) {
            logger.log(Level.INFO, "Employee " + id + " found: " + employeeDTOOptional.get());
            return new ResponseEntity<EmployeeDTO>(employeeDTOOptional.get(), HttpStatus.OK);
        } else {
            logger.log(Level.INFO, "Employee " + id + " not found");
            return new ResponseEntity<>("Employee " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/employees")
    public HttpEntity<?> getEmployeesByDeptOnDate(@RequestParam(name = "department") String deptName, @RequestParam(name = "date") LocalDate date, @RequestParam(name = "apiKey") String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<List<EmployeeDTO>> employees = employeesService.getEmployeesByDepartmentNameOnDate(deptName, date);
        if(employees.isPresent()) {
            return new ResponseEntity<>(employees.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No employees found working in the " + deptName + " department on " + Utility.getDateAsString(date), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/managers")
    public ResponseEntity<?> getManagersByDeptChronologically(@RequestParam(name = "department") String deptName, @RequestParam(name = "apiKey") String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<List<EmployeeDTO>> managers = employeesService.getManagersByDepartmentChronologically(deptName);
        if(managers.isPresent()) {
            return new ResponseEntity<>(managers.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No managers found from the " + deptName + " department", HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/employee/lastName")
    public ResponseEntity<?> getEmployeesByLastName(@RequestParam String lastName, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 2;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<List<EmployeeDTO>> list = employeesService.getEmployeesByLastName(lastName);
        if (list.isPresent()) {
            return new ResponseEntity<>(list.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee with last name: " + lastName + ", not found", HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/salary/range") // example = /salary/range?jobTitle=Senior+Engineer&year=1986
    public HttpEntity<?> getLowestAndHighestSalaryForJobTitleDuringAYear(@RequestParam String jobTitle, @RequestParam int year, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<Map<String, BigDecimal>> map = salariesService.getLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);
        if (map.isPresent()) {
            return new ResponseEntity<>(map.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for job title: " + jobTitle + ", year: " + year, HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/salary/genderPayGap") // example = /salary/genderPayGap?fromYear=1980&toYear=2000
    public HttpEntity<?> getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(@RequestParam int fromYear, @RequestParam int toYear, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<List<Object[]>> list = salariesService.getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);
        if (list.isPresent()) {
            return new ResponseEntity<>(list.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for the percentage gender pay gap between years: " + fromYear + " and " + toYear, HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/salary/department/average") // example = /salary/department/average?department=Finance&date=1988-10-23
    public HttpEntity<?> getAverageSalaryForDepartmentOnGivenDate(@RequestParam String department, @RequestParam LocalDate date, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<Map<String, BigDecimal>> map = salariesService.getAverageSalaryForDepartmentOnGivenDate(department, date);
        if (map.isPresent()) {
            return new ResponseEntity<>(map.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for department: " + department + ", date: " + date, HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/salary/progression") // example = /salary/progression?empNo=10001
    public HttpEntity<?> getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(@RequestParam int empNo, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<List<Integer>> list = salariesService.getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);
        if (list.isPresent()) {
            return new ResponseEntity<>(list.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No results found for employee number: " + empNo, HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/department")
    public ResponseEntity<?> getDepartmentById(@RequestParam String id, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<DepartmentDTO> departmentDTOOptional = departmentRepository.findById(id);
        if (departmentDTOOptional.isPresent()) {
            logger.log(Level.INFO, "Department " + id + " found: " + departmentDTOOptional.get());
            return new ResponseEntity<DepartmentDTO>(departmentDTOOptional.get(), HttpStatus.OK);
        } else {
            logger.log(Level.INFO, "Department " + id + " not found");
            return new ResponseEntity<>("Department " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/salary")
    public ResponseEntity<?> getSalaryById(@RequestParam int id, @RequestParam LocalDate date, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 1;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        Optional<Integer> salary = salaryRepository.findById(id, date);
        if (salary.isPresent()) {
            logger.log(Level.INFO, "Salary " + id + " found: " + salary.get());
            return new ResponseEntity<>(salary.get(), HttpStatus.OK);
        } else {
            logger.log(Level.INFO, "Salary " + id + " not found");
            return new ResponseEntity<>("Salary " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
