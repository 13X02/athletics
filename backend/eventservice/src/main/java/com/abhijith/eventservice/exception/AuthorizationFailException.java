package com.abhijith.eventservice.exception;

public class AuthorizationFailException extends RuntimeException{
    public AuthorizationFailException(String message) {
        super(message);
    }
}
