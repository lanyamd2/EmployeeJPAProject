package com.bootswana.employeejpaproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiKeyNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ApiKeyNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String apiKeyNotFoundHandler(ApiKeyNotFoundException e){
        return e.getMessage();
    }
}
