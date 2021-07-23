package com.riobener.userservice.domain.model.exceptions;

public class FavoriteNotFoundException extends Exception{
    public FavoriteNotFoundException(String message) {
        super(message);
    }
}
