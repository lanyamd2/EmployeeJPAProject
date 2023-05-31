package com.bootswana.employeejpaproject.model.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    @DisplayName("Check Get API Access Level")
    void checkGetApiAccessLevel() {
        String key = "test123";
        System.out.println(apiKeyRepository.getApiAccessLevel(key));
        Assertions.assertEquals(1, apiKeyRepository.getApiAccessLevel(key));
    }

    @Test
    @DisplayName("Check insert new api key into database")
    void checkInsertNewApiKeyIntoDatabase() {

        String key = "generated123";
        int accessLevel = 3;

        apiKeyRepository.setApiKey(key, accessLevel);

        Assertions.assertEquals("[ApiKeyDTO{apiKey='generated123', accessLevel=3}]", apiKeyRepository.findById("generated123"));
    }

}
