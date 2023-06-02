package com.bootswana.employeejpaproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientNotAuthorisedAdvice {
    @ResponseBody
    @ExceptionHandler(ClientNotAuthorisedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String clientNotAuthorised(ClientNotAuthorisedException e){
        return e.getMessage();
    }
}
