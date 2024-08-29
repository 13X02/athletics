package com.abhijith.eventservice.exception;

public class RegistrationNotFoundException extends RuntimeException{

    public RegistrationNotFoundException(String registrationId) {
        super("Registration not found: " + registrationId);
    }

}
