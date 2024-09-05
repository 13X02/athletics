package com.abhijith.usermanagementservice.exception;

public class CoachNotFoundException extends RuntimeException{
    public CoachNotFoundException(String message) {
        super(message);
    }
}
