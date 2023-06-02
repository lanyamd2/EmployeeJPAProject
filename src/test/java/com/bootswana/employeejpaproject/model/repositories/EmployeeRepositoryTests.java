package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class EmployeeRepositoryTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Test
    void contextLoads() {
    }

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
    @DisplayName("Check that employees by department name query returns a filled list when requesting for a valid department")
    void checkForEmployeesByDepartmentName() {
        List<EmployeeDTO> employees = employeeRepository.findEmployeesByDepartmentNameOnDate("Customer Service", LocalDate.of(2000, 1, 1));
        Assertions.assertTrue(employees.size() >= 1);
    }

    @Test
    @DisplayName("Check that employees by department name query returns an empty list when requesting for an invalid department")
    void checkForEmployeesByInvalidDepartmentName() {
        List<EmployeeDTO> employees = employeeRepository.findEmployeesByDepartmentNameOnDate("", LocalDate.of(2000, 1, 1));
        Assertions.assertFalse(employees.size() >= 1);
    }

    @Test
    @DisplayName("Check that managers by department name query returns a filled list when requesting for a valid department")
    void checkForManagersByDepartmentName() {
        List<IManagerProjection> managersAndDates = employeeRepository.findManagersAndDatesByDepartmentNameChronologically("Customer Service");
        Assertions.assertTrue(managersAndDates.size() >= 1);
    }

    @Test
    @DisplayName("Check that managers by department name query returns an empty list when requesting for a non-existent department")
    void checkForManagersByNonExistentDepartmentName() {
        List<IManagerProjection> managersAndDates = employeeRepository.findManagersAndDatesByDepartmentNameChronologically("Human Resources");
        Assertions.assertFalse(managersAndDates.size() <= 1);
    }
}

