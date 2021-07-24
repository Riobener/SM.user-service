package com.riobener.userservice.domain.model.exceptions;

public class EmptyUserFavoritesException extends Exception{
    public EmptyUserFavoritesException(String message) {
        super(message);
    }
}
