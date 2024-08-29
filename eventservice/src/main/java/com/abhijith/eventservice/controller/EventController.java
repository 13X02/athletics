package com.abhijith.eventservice.controller;

import com.abhijith.eventservice.dto.EventRequestDto;
import com.abhijith.eventservice.dto.EventResponseDto;
import com.abhijith.eventservice.dto.RegistrationRequestDto;
import com.abhijith.eventservice.dto.ResultRequestDto;
import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.Registration;
import com.abhijith.eventservice.model.Result;
import com.abhijith.eventservice.service.EventService;
import com.abhijith.eventservice.service.RegistrationService;
import com.abhijith.eventservice.service.ResultService;
import com.abhijith.eventservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RegistrationService registrationService;


    @Autowired
    private ResultService resultService;


    // Event Endpoints
    @GetMapping("/all")
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        List<EventResponseDto> eventResponse = new ArrayList<>();
        eventResponse= events.stream().map(AppUtils::toEventResponse).toList();
        return new ResponseEntity<>(eventResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventRequestDto eventRequestDto) {

            Event createdEvent = eventService.createEvent(eventRequestDto);
            return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        Event event = eventService.getEventById(id);

            return new ResponseEntity<>(event, HttpStatus.OK);

    }

    // Registration Endpoints
    @PostMapping("/register")
    public ResponseEntity<Registration> saveRegistration(@RequestBody RegistrationRequestDto registrationRequestDto) {

           Registration savedRegistration = registrationService.save(registrationRequestDto);
            return new ResponseEntity<>(savedRegistration, HttpStatus.CREATED);

    }

    @GetMapping("/registrations/pending")
    public ResponseEntity<List<Registration>> findPendingRegistrations() {
        List<Registration> pendingRegistrations = registrationService.findPendingRegistrations();
        return new ResponseEntity<>(pendingRegistrations, HttpStatus.OK);
    }

    @GetMapping("/registrations/athlete/{athleteId}")
    public ResponseEntity<List<Registration>> findRegistrationsByAthlete(@PathVariable String athleteId) {
        List<Registration> registrations = registrationService.findRegistrationsByAthlete(athleteId);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @PutMapping("/registrations/{id}/approve")
    public ResponseEntity<Registration> updateRegistration(@PathVariable String id) {
            Registration updatedRegistration = registrationService.approveRegistration(id);
            return new ResponseEntity<>(updatedRegistration, HttpStatus.OK);

    }

    @PutMapping("/registrations/{id}/reject")
    public ResponseEntity<Registration> rejectRegistration(@PathVariable String id) {

            Registration updatedRegistration = registrationService.rejectRegistration(id);
            return new ResponseEntity<>(updatedRegistration, HttpStatus.OK);

    }

    @GetMapping("/registrations/event/{id}")
    public ResponseEntity<List<Registration>> getRegistrationByEvent(@PathVariable String id) {

            List<Registration> registrations = registrationService.getRegistrationByEvent(id);

                return new ResponseEntity<>(registrations, HttpStatus.OK);


    }

    @PostMapping("/result")
    public ResponseEntity<Result> createResult(@RequestBody ResultRequestDto resultRequestDto){
        return new ResponseEntity<>(resultService.createResult(resultRequestDto),HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}/result")
    public ResponseEntity<List<Result>> getResultsByEvent(@PathVariable String eventId){
        return new ResponseEntity<>(resultService.findResultByEventId(eventId), HttpStatus.OK);
    }
    @GetMapping("/results")
    public ResponseEntity<List<Result>> getAllResults(){
        return new ResponseEntity<>(resultService.getAllResults(), HttpStatus.OK);
    }


}
