package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class DepartmentControllerTests {
    @Test
    @DisplayName("Check if the exceptions are thrown if the incorrect parameters are entered for getDepartmentCount")
    void checkApiKeyException() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange("http://localhost:8080/departments?from=1986&to=1987&apiKey=doesNotExist", HttpMethod.GET,null, new ParameterizedTypeReference<HashMap<String,Integer>>(){});
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("Could not find existing API key: doesNotExist")){
                fail();
            }
        }
    }
    @Test
    @DisplayName("Check if the exceptions are thrown if the incorrect parameters are entered for getDepartmentCount")
    void checkMethodArgumentMismatchException() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange("http://localhost:8080/departments?from=19jhk6&to=1987&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", HttpMethod.GET,null, new ParameterizedTypeReference<HashMap<String,Integer>>(){});
            fail();
        } catch (HttpClientErrorException e) {
            if (!e.toString().contains("Parameter is of not the correct type, should be integer e.g. 1986")){
                fail();
            }
        }
    }
    @Test
    @DisplayName("Check if the status code is 200 if the correct parameters are entered for getDepartmentCount")
    void checkGetDepartmentCountOkRequest(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HashMap<String,Integer>> response=restTemplate.exchange("http://localhost:8080/departments?from=1986&to=1987&apiKey=u4Ip9hbD7VyQqWDrrfjw16_PjtqyRJD8", HttpMethod.GET,null, new ParameterizedTypeReference<HashMap<String,Integer>>(){});
        Assertions.assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
    }


}
