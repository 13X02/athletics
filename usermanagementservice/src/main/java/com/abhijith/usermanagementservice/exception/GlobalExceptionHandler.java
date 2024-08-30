package com.abhijith.usermanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationFailException.class)
    public ResponseEntity<String> handleAuthorization(AuthorizationFailException ex){
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CoachNotFoundException.class)
    public ResponseEntity<String> handleCoachNotFound(CoachNotFoundException ex){
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AthleteNotFoundException.class)
    public ResponseEntity<String> handleAthleteNotFound(AthleteNotFoundException ex){
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
