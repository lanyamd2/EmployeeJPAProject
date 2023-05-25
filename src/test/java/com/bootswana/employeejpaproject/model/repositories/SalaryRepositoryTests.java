package com.bootswana.employeejpaproject.model.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class SalaryRepositoryTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("checkAverageSalaryFromDepartmentGivenDate")
    void checkAverageSalaryFromDepartmentGivenDate() {
        BigDecimal expected = BigDecimal.valueOf(68567.95);
        String department = "Finance";
        LocalDate date = LocalDate.of(1995, 6, 26);

        Map<String, BigDecimal> salaryMap = salaryRepository.getAverageSalaryForDepartmentOnGivenDate(department, date);
        BigDecimal averageSalary = salaryMap.get("average_salary");

        System.out.println("Average Salary: " + averageSalary + " converted to 2 decimal places.");

        assertEquals(expected.doubleValue(), salaryMap.get("average_salary").doubleValue());
    }
}