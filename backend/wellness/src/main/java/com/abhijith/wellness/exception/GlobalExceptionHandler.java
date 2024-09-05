package com.abhijith.wellness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationFailException.class)
    public ResponseEntity<String> handleAuthorizationFailException(AuthorizationFailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AthleteNotFoundException.class)
    public ResponseEntity<String> handleAthleteNotFoundException(AthleteNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(WeightPlanNotFoundException.class)
    public ResponseEntity<String> handleWeightPlanNotFoundException(WeightPlanNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
