package com.bootswana.employeejpaproject.exception;

public class ApiKeyNotFoundException extends Exception{
    public ApiKeyNotFoundException(String apiKey) {
        super("Could not find existing API key: "+apiKey);
    }
}
