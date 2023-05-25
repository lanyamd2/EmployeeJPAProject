package com.bootswana.employeejpaproject.model.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeRepositoryTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Test
    @DisplayName("Check Get Employee By Last Name")
    void checkGetEmployeeByExistingLastName() {
        String lastName = "Aamodt";
        employeeRepository.getEmployeesByLastName(lastName).forEach(e -> Assertions.assertEquals(lastName, e.getLastName()));
    }

    @Test
    @DisplayName("Check Get Employee By Last Name")
    void checkGetEmployeeByNonExistingLastName() {
        String lastName = "safsdfsdf";
        employeeRepository.getEmployeesByLastName(lastName).forEach(e -> Assertions.assertEquals(lastName, e.getLastName()));
    }

    @Test
    void contextLoads() {
    }

}