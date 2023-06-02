package com.bootswana.employeejpaproject.exception;

public class ClientNotAuthorisedException extends Exception{
    public ClientNotAuthorisedException() {
        super("You are not authorised to perform this action.");
    }
}
