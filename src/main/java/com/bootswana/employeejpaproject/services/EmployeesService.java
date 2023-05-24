package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {
    public EmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    private final EmployeeRepository employeeRepository;

}
