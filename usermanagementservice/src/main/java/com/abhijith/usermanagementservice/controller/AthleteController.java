package com.abhijith.usermanagementservice.controller;

import com.abhijith.usermanagementservice.client.UserRole;
import com.abhijith.usermanagementservice.dto.AthleteRequestDto;
import com.abhijith.usermanagementservice.dto.AssistanceRequestDto;
import com.abhijith.usermanagementservice.dto.UserInfo;
import com.abhijith.usermanagementservice.feign.FeignClientService;
import com.abhijith.usermanagementservice.model.Athlete;
import com.abhijith.usermanagementservice.model.AssistanceRequest;
import com.abhijith.usermanagementservice.service.AthleteService;
import com.abhijith.usermanagementservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FeignClientService feignClientService;

    @PostMapping("/create")
    public ResponseEntity<Athlete> createProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                 @RequestParam("firstName") String firstName,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("birthDate") Date birthDate,
                                                 @RequestParam("gender") String gender,
                                                 @RequestParam("height") String height,
                                                 @RequestParam("weight") String weight,
                                                 @RequestParam("category") String category,
                                                 @RequestParam("coachId") String coachId,
                                                 @RequestParam("photo") MultipartFile photo) throws IOException {

        AthleteRequestDto athleteRequestDto = new AthleteRequestDto(
                firstName, lastName, birthDate, gender, height, weight, category, coachId
        );
        UserInfo userInfo = jwtService.extractUserInfo(authHeader);
        if (feignClientService.validateId(userInfo.getUserId())) {
            if (userInfo.getUserRole().equals(UserRole.ATHLETE)) {
                Athlete athlete = athleteService.createProfile(athleteRequestDto, userInfo.getUserId(), photo);
                return new ResponseEntity<>(athlete, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Athlete> editAthlete(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                               @RequestParam("firstName") String firstName,
                                               @RequestParam("lastName") String lastName,
                                               @RequestParam("birthDate") Date birthDate,
                                               @RequestParam("gender") String gender,
                                               @RequestParam("height") String height,
                                               @RequestParam("weight") String weight,
                                               @RequestParam("category") String category,
                                               @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {

        AthleteRequestDto athleteRequestDto = new AthleteRequestDto(
                firstName, lastName, birthDate, gender, height, weight, category, null
        );
        UserInfo userInfo = jwtService.extractUserInfo(authHeader);

        if (feignClientService.validateId(userInfo.getUserId())) {
            if (userInfo.getUserRole().equals(UserRole.ATHLETE)) {
                Athlete athlete = athleteService.editAthlete(athleteRequestDto, userInfo.getUserId(), photo);
                return new ResponseEntity<>(athlete, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/request-assistance")
    public ResponseEntity<AssistanceRequest> requestAssistance(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                               @RequestParam("coachId") String coachId,
                                                               @RequestParam("message") String message) {
        UserInfo userInfo = jwtService.extractUserInfo(authHeader);

        if (feignClientService.validateId(userInfo.getUserId())) {
            if (userInfo.getUserRole().equals(UserRole.ATHLETE)) {
                AssistanceRequestDto assistanceRequestDto = new AssistanceRequestDto(coachId, message);
                AssistanceRequest assistanceRequest = athleteService.requestAssistance(assistanceRequestDto, userInfo.getUserId());
                if (assistanceRequest != null) {
                    return new ResponseEntity<>(assistanceRequest, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable String id) {
        Athlete athlete = athleteService.getAthleteById(id);
        if (athlete != null) {
            return new ResponseEntity<>(athlete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        List<Athlete> athletes = athleteService.getAll();
        return new ResponseEntity<>(athletes, HttpStatus.OK);
    }

    @GetMapping("validate")
    public Boolean validateAthleteId(@RequestParam("id") String id) {
        return athleteService.validateAthlete(id);
    }

    @GetMapping("/findByUserId")
    public String findAthleteByUserId(@RequestParam("userId") String userId) {
        return athleteService.findAthleteByUserId(userId);
    }
}
