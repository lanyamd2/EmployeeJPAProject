package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.exception.ClientNotAuthorisedException;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import com.bootswana.employeejpaproject.service.EmployeesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class EmployeeController {
    static Logger logger = Logger.getLogger(EmployeeController.class.getName());

    private EmployeesService employeesService;
    private ApiKeyService apiKeyService;

    @Autowired
    public EmployeeController(EmployeesService employeesService, ApiKeyService apiKeyService) {
        this.employeesService = employeesService;
        this.apiKeyService = apiKeyService;
    }

    @PutMapping("/employee/create")
    public ResponseEntity<?> createEmployee(
            @RequestParam Integer id, @RequestParam LocalDate birthDate,
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String gender, @RequestParam LocalDate hireDate,
            @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {

        int accessLevel = 2;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        if(!isGenderValid(gender)){
            return  new ResponseEntity<>("Gender fields must be M or F.",HttpStatus.BAD_REQUEST );
        }
        String message = employeesService.createNewEmployee(new EmployeeDTO(id,birthDate,firstName,lastName,gender,hireDate));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/employee/update")
    public ResponseEntity<?> updateEmployee(
            @RequestParam Integer id, @RequestParam LocalDate birthDate,
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String gender, @RequestParam LocalDate hireDate,
            @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 2;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        if(!isGenderValid(gender)){
            return  new ResponseEntity<>("Gender fields must be M or F.",HttpStatus.BAD_REQUEST );
        }
        String message = employeesService.updateExistingEmployee(new EmployeeDTO(id,birthDate,firstName,lastName,gender,hireDate));
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @DeleteMapping("/employee")
    public ResponseEntity<?> deleteEmployee(@RequestParam int id, @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 3;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        String message = employeesService.deleteEmployeeById(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    public boolean isGenderValid(String gender){
        if(gender.equals("M")){
            return true;
        } else if (gender.equals("F")) {
            return true;
        }
        return false;
    }
}
