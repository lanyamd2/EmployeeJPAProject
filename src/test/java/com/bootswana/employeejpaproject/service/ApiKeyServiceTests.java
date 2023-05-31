package com.bootswana.employeejpaproject.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiKeyServiceTests {

    @Autowired
    ApiKeyService apiKeyService;
    @Test
    @DisplayName("Check for ")
    void checkFor(){
        System.out.println(apiKeyService.getAccessLevel("1234567890"));
    }
}
