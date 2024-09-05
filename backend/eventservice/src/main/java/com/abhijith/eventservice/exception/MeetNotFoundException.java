package com.abhijith.eventservice.exception;

public class MeetNotFoundException extends RuntimeException{
    public MeetNotFoundException(String meetId) {
        super("Meet ID not found: " + meetId);
    }
}
