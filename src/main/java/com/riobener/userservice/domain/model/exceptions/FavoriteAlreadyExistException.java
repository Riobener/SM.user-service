package com.riobener.userservice.domain.model.exceptions;

public class FavoriteAlreadyExistException extends Exception {
    public FavoriteAlreadyExistException(String message) {
        super(message);
    }
}
