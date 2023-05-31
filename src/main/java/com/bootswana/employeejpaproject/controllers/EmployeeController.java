package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
