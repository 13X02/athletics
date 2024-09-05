package com.abhijith.eventservice.exception;

public class AthleteNotFoundException extends RuntimeException{
    public AthleteNotFoundException(String message) {
        super(message);
    }
}
