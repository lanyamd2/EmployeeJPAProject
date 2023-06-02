package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.exception.ClientNotAuthorisedException;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class EmployeeController {
    static Logger logger = Logger.getLogger(EmployeeController.class.getName());

    private EmployeeRepository employeeRepository;
    private EmployeesService employeesService;
    private ApiKeyService apiKeyService;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, EmployeesService employeesService, ApiKeyService apiKeyService) {
        this.employeeRepository = employeeRepository;
        this.employeesService = employeesService;
        this.apiKeyService = apiKeyService;
    }

    //create
    /* using @RequestBody for EmployeeDTO and @RequestParam for
            if client is not authorised (level 2 or 3 is valid)
                then throw exception
            else
                check if id already exists
                    if it does tell user that the id is already in use
                else
                    validate entries and save to database
     */
    @PutMapping("/employee/create")
    public ResponseEntity<?> createEmployee(
            @RequestBody EmployeeDTO employeeDTO,
            @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 2;
        apiKeyService.checkAccessRights(apiKey, accessLevel);

        String message=employeesService.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //update

    @DeleteMapping("/employee/delete")
    public ResponseEntity<?> deleteEmployee(@RequestParam int id, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 3;
        apiKeyService.checkAccessRights(apiKey, accessLevel);

        String message = employeesService.deleteEmployeeById(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
