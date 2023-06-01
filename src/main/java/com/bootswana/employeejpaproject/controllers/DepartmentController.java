package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class DepartmentController {
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

}
