package com.riobener.userservice.infrastructure.exceptions;

public class RestFileNotFoundException extends Exception{
    public RestFileNotFoundException(String message) {
        super(message);
    }
}
