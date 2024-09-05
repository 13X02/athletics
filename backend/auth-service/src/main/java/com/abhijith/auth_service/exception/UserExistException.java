package com.abhijith.auth_service.exception;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
