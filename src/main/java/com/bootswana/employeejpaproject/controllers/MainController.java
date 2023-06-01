package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.EmployeeJpaProjectApplication;
import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
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
import org.springframework.web.bind.annotation.RestController;

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
    public MainController(EmployeeRepository employeeRepository, EmployeesService employeesService, DepartmentsService departmentsService, SalariesService salariesService, ApiKeyService apiKeyService) {
        this.employeeRepository = employeeRepository;
        this.employeesService = employeesService;
        this.departmentsService = departmentsService;
        this.salariesService = salariesService;
        this.apiKeyService=apiKeyService;
    }

    @GetMapping("/employee")//api key to be implemented, also try catch for MethodArgumentMismatch
    public ResponseEntity<?> getEmployeeById(@RequestParam int id, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        int level = apiKeyService.getAccessLevel(apiKey);
        Optional<EmployeeDTO> employeeDTOOptional = employeeRepository.findById(id);
        if (employeeDTOOptional.isPresent()) {
            logger.log(Level.INFO,"Employee "+id+" found: "+employeeDTOOptional.get());
            return new ResponseEntity<EmployeeDTO>(employeeDTOOptional.get(), HttpStatus.OK);
        } else {
            logger.log(Level.INFO, "Employee " + id + " not found");
            return new ResponseEntity<>("Employee " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
