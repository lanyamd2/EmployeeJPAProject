package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;

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
                "http://localhost:8080/employees?department=Finance&date=2000-01-01&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8",
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
            restTemplate.getForEntity("http://localhost:8080/employees?department=Technology&date=2000-01-01&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No employees found working in the Technology department on 2000-01-01")){
                fail();
            }
        }
    }

    @Test
    @DisplayName("test managers by invalid department")
    void testManagersEndpointByInvalidDept() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity("http://localhost:8080/employees/managers?department=Technology&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", String.class);
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("No managers found from the Technology department")){
                fail();
            }
        }
    }

}
