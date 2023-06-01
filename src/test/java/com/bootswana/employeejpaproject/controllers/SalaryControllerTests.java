package com.bootswana.employeejpaproject.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootTest
public class SalaryControllerTests {
    @Test
    @DisplayName("Check if the status code is 200 if the correct parameters are entered for findAverageSalary")
    void checkFindAverageSalaryOkRequest(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=restTemplate.exchange("http://localhost:8080/salary/average?title=Engineer&from=1986&to=1987&apiKey=test123", HttpMethod.GET,null, new ParameterizedTypeReference<String>(){});
        Assertions.assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
    }

}
