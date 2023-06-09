package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiKeyServiceTests {

    @Autowired
    ApiKeyService apiKeyService;

    public static Stream<Arguments> checkGetAccessLevel() {
        return Stream.of(
                Arguments.arguments("test123",1),
                Arguments.arguments("0987654321",2),
                Arguments.arguments("1234567890",3)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that method returns the correct access level")
    void checkGetAccessLevel(String key, int expected) {
        try {
            Assertions.assertEquals(expected,apiKeyService.getAccessLevel(key));
        } catch (ApiKeyNotFoundException e) {
            System.out.println("Api Key Not Found");
        }
    }

    @Test
    @DisplayName("Check that method throws exception if API key is not found ")
    void checkGetAccessLevelThrowsException(){
        Assertions.assertThrows(ApiKeyNotFoundException.class,()->apiKeyService.getAccessLevel("doesNotExist"));
    }

    @Test
    @DisplayName("Check if key with access level 1 can be generated")
    void checkIfKeyWithAccessLevel1CanBeGenerated() {
        apiKeyService.generateApiKey(1);
    }

    @Test
    @DisplayName("Check if key with access level 2 can be generated")
    void checkIfKeyWithAccessLevel2CanBeGenerated() {
        apiKeyService.generateApiKey(2);
    }

    @Test
    @DisplayName("Check if key with access level 3 can be generated")
    void checkIfKeyWithAccessLevel3CanBeGenerated() {
        apiKeyService.generateApiKey(3);
    }

    @Test
    @DisplayName("Check if api key exists")
    void checkIfApiKeyExists() {
        assertTrue(apiKeyService.isApiKeyExisting("FynHSkcoZzbPpGaIERHJUGZWD84YFS2Z"));
    }

    @Test
    @DisplayName("Check if api key doesn't exists")
    void checkIfApiKeyDoesntExists() {
        assertFalse(apiKeyService.isApiKeyExisting("notAValidApiKey"));
    }
}
