package com.abhijith.eventservice.exception;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException(String athleticId){
        super("Athlete"+athleticId+" already registered for event ");
    }
}
