package com.abhijith.eventservice.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String eventId) {
        super("Event ID not found: " + eventId);
    }
}
