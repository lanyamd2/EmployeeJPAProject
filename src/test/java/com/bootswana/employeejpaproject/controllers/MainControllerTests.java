package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MainControllerTests {
    @Autowired
    EmployeesService employeesService;

    @Autowired
    DepartmentsService departmentsService;

    @Autowired
    SalariesService salariesService;

    @Test
    @DisplayName("test employees by valid department and date")
    void testEmployeesEndpointByValidDeptAndDate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(
                "http://localhost:8080/employees?department=Finance&date=2000-01-01&apiKey=apiKey1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
          );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
  
    @DisplayName("Test Valid Employees By last Name Endpoint")
    void testValidEmployeesByLastNameEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(
                "http://localhost:8080/employee/lastName?lastName=Facello&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("test employees by invalid department and date")
    void testEmployeesEndpointByInvalidDeptAndDate() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/employees?department=Technology&date=2000-01-01&apiKey=apiKey1", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No employees found working in the Technology department on 2000-01-01")){
              fail();
            }
        }
    }
  
    @Test
    @DisplayName("Test Invalid Employees By Last Name Endpoint")
    void testInvalidEmployeesByLastNameEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/employee/lastName?lastName=apple&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("Employee with last name: apple, not found")){
                fail();
            }
        }
    }

    @Test
    @DisplayName("Test Valid Lowest And Highest Salary For Job Title During A Year Endpoint")
    void testValidLowestAndHighestSalaryForJobTitleDuringAYearEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, BigDecimal>> response = restTemplate.exchange(
                "http://localhost:8080/salary/range?title=Senior+Engineer&year=1986&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Invalid Lowest And Highest Salary For Job Title During A Year Endpoint")
    void testInvalidLowestAndHighestSalaryForJobTitleDuringAYearEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/salary/range?title=Senior+Engineer&year=300&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No results found for job title: Senior Engineer, year: 300")){
                fail();
            }
        }
    }

    @Test
    @DisplayName("test managers by valid department")
    void testManagersEndpointByValidDept() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(
                "http://localhost:8080/employees/managers?department=Finance&apiKey=apiKey1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
  
    @DisplayName("Test Valid Gender Pay Gap Percentage Between Two Years For Each Job Title Endpoint")
    void testValidGenderPayGapPercentageBetweenTwoYearsForEachJobTitleEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Object[]>> response = restTemplate.exchange(
                "http://localhost:8080/salary/genderPayGap?from=1998&to=2000&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Invalid Gender Pay Gap Percentage Between Two Years For Each Job Title Endpoint")
    void testInvalidGenderPayGapPercentageBetweenTwoYearsForEachJobTitleEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/salary/genderPayGap?from=298&to=300&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No results found for the percentage gender pay gap between years: 298 and 300")){
                fail();
            }
        }
    }

    @Test
    @DisplayName("Test Valid Average Salary For Department On Given Date Endpoint")
    void testValidAverageSalaryForDepartmentOnGivenDateEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, BigDecimal>> response = restTemplate.exchange(
                "http://localhost:8080/salary/department/average?department=Finance&date=1988-10-23&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("test managers by invalid department")
    void testManagersEndpointByInvalidDept() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/employees/managers?department=Technology&apiKey=apiKey1", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No managers found from the Technology department")){
              fail();
            }
        }
    }
  
    @DisplayName("Test Invalid Average Salary For Department On Given Date Endpoint")
    void testInvalidAverageSalaryForDepartmentOnGivenDateEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/salary/department/average?department=Finance&date=1000-10-23&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No results found for department: Finance, date: 1000-10-23")){
                fail();
            }
        }
    }

    @Test
    @DisplayName("Test Valid First Five Salaries Of An Employee By Employee Number Endpoint")
    void testValidFirstFiveSalariesOfAnEmployeeByEmployeeNumberEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                "http://localhost:8080/salary/progression?empNo=10001&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Invalid First Five Salaries Of An Employee By Employee Number Endpoint")
    void testInvalidFirstFiveSalariesOfAnEmployeeByEmployeeNumberEndpoint() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/salary/progression?empNo=1&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No results found for employee number: 1")){
                fail();
            }
        }
    }
}
