package com.abhijith.usermanagementservice.exception;

public class DuplicateRequestException extends RuntimeException{
    public DuplicateRequestException(String message) {
        super(message);
    }
}
