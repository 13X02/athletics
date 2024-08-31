package com.abhijith.eventservice.controller;

import com.abhijith.eventservice.dto.*;
import com.abhijith.eventservice.feign.FeignClientService;
import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.Registration;
import com.abhijith.eventservice.model.Result;
import com.abhijith.eventservice.service.EventService;
import com.abhijith.eventservice.service.JwtService;
import com.abhijith.eventservice.service.RegistrationService;
import com.abhijith.eventservice.service.ResultService;
import com.abhijith.eventservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
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
    private FeignClientService feignClientService;

    @Autowired
    private JwtService jwtService;




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

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHead,
                                             @RequestParam("eventTitle") String eventTitle,
                                             @RequestParam("eventDate") Date eventDate,
                                             @RequestParam("meetId") String meetId,
                                             @RequestParam("venue") String venue,
                                             @RequestParam("category") String category,
                                             @RequestParam("eventDescription") String eventDescription,
                                             @RequestParam("photo") MultipartFile photo) throws IOException {

        // Extract user info from the JWT
        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        // Validate user ID and check for ADMIN role
        if (feignClientService.validateId(userInfo.getUserId())) {
            if (userInfo.getRole().equals(UserRole.ADMIN)) {
                // Create the EventRequestDto from the request parameters
                EventRequestDto eventRequestDto = new EventRequestDto(
                        eventTitle, eventDate, meetId, venue, category, eventDescription
                );

                // Call the service method to create the event, including the photo
                Event createdEvent = eventService.createEvent(eventRequestDto, photo);
                return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        Event event = eventService.getEventById(id);

            return new ResponseEntity<>(event, HttpStatus.OK);

    }
    @GetMapping("/statistics")
    public EventStatsResponse getEventStatistics() {
        return eventService.getEventStatistics();
    }

    // Registration Endpoints
    @PostMapping("/register")
    public ResponseEntity<Registration> saveRegistration(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHead,@RequestBody RegistrationRequestDto registrationRequestDto) {
        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getRole().equals(UserRole.ATHLETE)){
                return new ResponseEntity<>(registrationService.save(registrationRequestDto), HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/registrations/pending")
    public ResponseEntity<List<Registration>> findPendingRegistrations(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHead) {

        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getRole().equals(UserRole.ADMIN)){
                return new ResponseEntity<>(registrationService.findPendingRegistrations(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/registrations/athlete/{athleteId}")
    public ResponseEntity<List<Registration>> findRegistrationsByAthlete(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHead,@PathVariable String athleteId) {

        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getRole().equals(UserRole.ATHLETE)){
                return new ResponseEntity<>(registrationService.findRegistrationsByAthlete(athleteId), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/registrations/{id}/approve")
    public ResponseEntity<Registration> updateRegistration(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHead,@PathVariable String id) {
        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getRole().equals(UserRole.ADMIN)){
                return new ResponseEntity<>(registrationService.approveRegistration(id), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);




    }

    @PutMapping("/registrations/{id}/reject")
    public ResponseEntity<Registration> rejectRegistration(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHead,@PathVariable String id) {
        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getRole().equals(UserRole.ADMIN)){
                return new ResponseEntity<>(registrationService.rejectRegistration(id), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/registrations/event/{id}")
    public ResponseEntity<List<Registration>> getRegistrationByEvent(@RequestHeader(HttpHeaders.AUTHORIZATION)String authHead,@PathVariable String id) {

        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getRole().equals(UserRole.ADMIN)){
                return new ResponseEntity<>(registrationService.getRegistrationByEvent(id), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);



    }

    @PostMapping("/result")
    public ResponseEntity<Result> createResult(@RequestHeader(HttpHeaders.AUTHORIZATION)String authHead,@RequestBody ResultRequestDto resultRequestDto){
        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getRole().equals(UserRole.ADMIN)){
                return new ResponseEntity<>(resultService.createResult(resultRequestDto), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{eventId}/result")
    public ResponseEntity<List<Result>> getResultsByEvent(@PathVariable String eventId){
        return new ResponseEntity<>(resultService.findResultByEventId(eventId), HttpStatus.OK);
    }
    @GetMapping("/results")
    public ResponseEntity<List<Result>> getAllResults(){
        return new ResponseEntity<>(resultService.getAllResults(), HttpStatus.OK);
    }

    @GetMapping("/results/{athleteId}")
    public ResponseEntity<List<Result>> getResultsByAthlete(@PathVariable String athleteId){
        return new ResponseEntity<>(resultService.findResultByAthleteId(athleteId),HttpStatus.OK);
    }


}
