package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.exception.ClientNotAuthorisedException;
import com.bootswana.employeejpaproject.model.dtos.DepartmentDTO;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class DepartmentController {
    static Logger logger = Logger.getLogger(DepartmentController.class.getName());
    private DepartmentRepository departmentRepository;
    private DepartmentsService departmentsService;
    private ApiKeyService apiKeyService;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository, DepartmentsService departmentsService, ApiKeyService apiKeyService) {
        this.departmentRepository = departmentRepository;
        this.departmentsService = departmentsService;
        this.apiKeyService=apiKeyService;
    }
    @GetMapping("/departments")
    public ResponseEntity<?> getDepartmentCount(@RequestParam int from, @RequestParam int to, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        if (from > to) {
            return new ResponseEntity<>("Invalid year range, the year " + from + " needs to be after " + to, HttpStatus.BAD_REQUEST);
        } else {
            HashMap<String, Integer> result = departmentsService.createDepartmentSummary(from, to);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PutMapping("/department/create")
    public ResponseEntity<?> createDepartment(
            @RequestBody DepartmentDTO departmentDTO,
            @RequestParam String apiKey) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int accessLevel = 2;
        apiKeyService.checkAccessRights(apiKey, accessLevel);
        String message = departmentsService.createNewDepartment(departmentDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
