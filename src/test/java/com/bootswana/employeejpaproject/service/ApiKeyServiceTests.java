package com.bootswana.employeejpaproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApiKeyServiceTests {

    @Autowired
    ApiKeyService apiKeyService;
    @Test
    @DisplayName("Check for ")
    void checkFor(){
        System.out.println(apiKeyService.getAccessLevel("1234567890"));
    }

    public static Stream<Arguments> checkGetAccessLevel() {
        return Stream.of(
                Arguments.arguments(null,0),
                Arguments.arguments("doesNotExist",0),
                Arguments.arguments("test123",1),
                Arguments.arguments("0987654321",2),
                Arguments.arguments("1234567890",3)
        );
    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that method returns the accurate access level")
    void checkGetAccessLevel(String key, int expected) {
        Assertions.assertEquals(expected,apiKeyService.getAccessLevel(key));
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
    @DisplayName("Check key with access level 1 can be used")
    void checkKeyWithAccessLevel1CanBeUsed() {
        assertEquals(1, apiKeyService.getAccessLevel("FynHSkcoZzbPpGaIERHJUGZWD84YFS2Z"));
    }

    @Test
    @DisplayName("Check key with access level 2 can be used")
    void checkKeyWithAccessLevel2CanBeUsed() {
        assertEquals(2, apiKeyService.getAccessLevel("4RdHk0WDmNK3kfg0LL7hMVJSNFCNZQ9T"));
    }

    @Test
    @DisplayName("Check key with access level 3 can be used")
    void checkKeyWithAccessLevel3CanBeUsed() {
        assertEquals(3, apiKeyService.getAccessLevel("_T6q6jHAO_zaCvWsDqcAEs8QphOEHzBl"));
    }
}
