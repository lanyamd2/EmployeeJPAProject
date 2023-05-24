package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeesService {
    private final EmployeeRepository employeeRepository;
    public EmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    public Optional<EmployeeDTO> getEmployeeByLastName(String lastName) {
//        // TODO
//        return Optional.empty();
//    }
}
