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

    @Test
    @DisplayName("Check Get API Access Level")
    void checkGetApiAccessLevel() {
        String key = "test123";
        System.out.println("Access level: " + apiKeyRepository.getApiAccessLevel(key));
        Assertions.assertEquals(1, apiKeyRepository.getApiAccessLevel(key));
    }

    @Test
    @DisplayName("Check Get API Access Level if API key does not exist")
    void checkGetApiAccessLevelNull() {
        String key = "doesNotExist";
        System.out.println("Access level: " + apiKeyRepository.getApiAccessLevel(key));
        Assertions.assertEquals(null, apiKeyRepository.getApiAccessLevel(key));
    }



    @Test
    @DisplayName("Check insert new api key into database")
    void checkInsertNewApiKeyIntoDatabase() {

        String key = "generated123";
        int accessLevel = 3;

//        apiKeyRepository.save(new ApiKeyDTO(key, accessLevel));

        String result = String.valueOf(apiKeyRepository.findById(key).get());;
        System.out.println(result);

        Assertions.assertEquals("ApiKeyDTO{apiKey='generated123', accessLevel=3}", result);
    }
}
