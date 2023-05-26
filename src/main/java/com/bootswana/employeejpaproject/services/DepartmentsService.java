package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.dtos.DepartmentDTO;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import com.bootswana.employeejpaproject.model.repositories.DeptEmpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsService {
    private final DepartmentRepository departmentRepository;

    public DepartmentsService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
}
