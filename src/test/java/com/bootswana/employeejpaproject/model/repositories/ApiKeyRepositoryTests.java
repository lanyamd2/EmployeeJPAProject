package com.bootswana.employeejpaproject.model.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiKeyRepositoryTests {

    @Autowired
    ApiKeyRepository apiKeyRepository;

    @Test
    @DisplayName("Check Get All Api Keys Exist")
    void checkGetAllApiKeysExist() {
        System.out.println(apiKeyRepository.getAllApiKeys());
        Assertions.assertFalse(apiKeyRepository.getAllApiKeys().isEmpty());
    }

}
