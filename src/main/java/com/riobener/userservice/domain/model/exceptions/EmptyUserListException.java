package com.riobener.userservice.domain.model.exceptions;

public class EmptyUserListException extends Exception{
    public EmptyUserListException(String message) {
        super(message);
    }
}
