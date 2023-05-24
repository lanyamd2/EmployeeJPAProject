package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

@Service
public class SalariesService {
    private final SalaryRepository salaryRepository;

    public SalariesService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }
}
