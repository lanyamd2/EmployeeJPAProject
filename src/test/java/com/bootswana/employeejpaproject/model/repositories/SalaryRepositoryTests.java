package com.bootswana.employeejpaproject.model.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalaryRepositoryTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Test
    void contextLoads() {
    }

}