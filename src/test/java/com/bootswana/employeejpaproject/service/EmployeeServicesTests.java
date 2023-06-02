package com.bootswana.employeejpaproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.stream.Stream;

@SpringBootTest
public class EmployeeServicesTests {
    @Autowired
    EmployeesService employeesService;
    public static Stream<Arguments> checkDeleteEmployee() {
        return Stream.of(
                Arguments.arguments(999999, "Employee " + 999999 + " found: " + "id=" + 999999 +
                        ", birthDate=1964-01-01" +
                        ", firstName='" + "Bob" + '\'' +
                        ", lastName='" + "Smith" + '\'' +
                        ", gender='" + "M" + '\'' +
                        ", hireDate=" + "2023-06-01"
                        + System.lineSeparator()
                        + "Employee with ID " + 999999 + " has been deleted"),
                Arguments.arguments(0, "Employee " + 0 + " does not exist")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check for method deleteEmployee ")
    void checkDeleteEmployee(int id, String expectedString) {
        String actual = employeesService.deleteEmployeeById(id);
        Assertions.assertEquals(expectedString,actual);
    }
}
