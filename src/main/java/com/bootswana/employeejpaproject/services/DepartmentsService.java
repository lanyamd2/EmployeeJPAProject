package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentsService {
    private final DepartmentRepository departmentRepository;

    public DepartmentsService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
}
