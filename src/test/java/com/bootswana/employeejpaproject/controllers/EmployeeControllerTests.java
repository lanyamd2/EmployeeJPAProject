package com.bootswana.employeejpaproject.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class EmployeeControllerTests {
    @Test
    @DisplayName("Check if the exceptions are thrown if the incorrect parameters are entered for deleteEmployee")
    void checkClientNotAuthorisedException() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange("http://localhost:8080/employee/delete?id=999999&apiKey=key2", HttpMethod.DELETE,null, new ParameterizedTypeReference<String>(){});
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("You are not authorised to perform this action")){
                fail();
            }
        }
    }

    @Test
    @DisplayName("Check if the status code is 200 if the correct parameters are entered for deleteEmployee")
    void checkDeleteEmployeeOkRequest(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=restTemplate.exchange("http://localhost:8080/employee/delete?id=0&apiKey=key3", HttpMethod.DELETE,null, new ParameterizedTypeReference<String>(){});
        Assertions.assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
    }
}
