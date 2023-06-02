package com.bootswana.employeejpaproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MethodArgumentMismatchAdvice {
    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException e){
        String message = "Parameter is of not the correct type";
        if (e.toString().contains("LocalDate")) {
            message += ", should be date e.g. 2000-12-28 (YYYY-MM-DD)";
        } else if (e.toString().contains("int") || e.toString().contains("Integer")) {
            message += ", should be integer e.g. 1986";
        }
        return message;
    }
}
