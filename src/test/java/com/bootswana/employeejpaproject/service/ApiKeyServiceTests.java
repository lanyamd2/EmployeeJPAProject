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
}
