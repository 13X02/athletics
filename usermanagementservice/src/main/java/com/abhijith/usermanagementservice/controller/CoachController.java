package com.abhijith.usermanagementservice.controller;

import com.abhijith.usermanagementservice.client.UserRole;
import com.abhijith.usermanagementservice.dto.CoachRequestDto;
import com.abhijith.usermanagementservice.dto.UserInfo;
import com.abhijith.usermanagementservice.feign.FeignClientService;
import com.abhijith.usermanagementservice.model.Achievments;
import com.abhijith.usermanagementservice.model.AssistanceRequest;
import com.abhijith.usermanagementservice.model.Coach;
import com.abhijith.usermanagementservice.service.CoachService;
import com.abhijith.usermanagementservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FeignClientService feignClientService;

    @PostMapping("/create")
    public ResponseEntity<Coach> createProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                               @RequestParam("firstName") String firstName,
                                               @RequestParam("lastName") String lastName,
                                               @RequestParam("birthDate") java.sql.Date birthDate,
                                               @RequestParam("gender") String gender,
                                               @RequestParam("category") String category,
                                               @RequestParam("photo") MultipartFile photo) throws IOException {

        UserInfo userInfo = jwtService.extractUserInfo(authHeader);
        CoachRequestDto coachRequestDto = new CoachRequestDto(firstName, lastName, birthDate, gender, category);
        if(feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.COACH)){
                Coach updatedCoach = coachService.createProfile(coachRequestDto, userInfo.getUserId(), photo);
                return new ResponseEntity<>(updatedCoach, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Coach> updateProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                               @RequestParam(value = "firstName", required = false) String firstName,
                                               @RequestParam(value = "lastName", required = false) String lastName,
                                               @RequestParam(value = "birthDate", required = false) java.sql.Date birthDate,
                                               @RequestParam(value = "gender", required = false) String gender,
                                               @RequestParam(value = "category", required = false) String category,
                                               @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {

        UserInfo userInfo = jwtService.extractUserInfo(authHeader);
        CoachRequestDto coachRequestDto = new CoachRequestDto(firstName, lastName, birthDate, gender, category);
        if(feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.COACH)){
                Coach updatedCoach = coachService.updateProfile(coachRequestDto, userInfo.getUserId(), photo);
                return new ResponseEntity<>(updatedCoach, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{coachId}")
    public ResponseEntity<Coach> getCoachById(@PathVariable String coachId) {
        try {
            Coach coach = coachService.findById(coachId);
            return new ResponseEntity<>(coach, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoaches() {
        List<Coach> coaches = coachService.findAll();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Coach>> searchByName(@RequestParam("name") String name) {
        List<Coach> coaches = coachService.searchByName(name);
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @PostMapping("/achievements")
    public ResponseEntity<Coach> setAchievements(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                 @RequestBody Achievments achievements) {
        String userId = jwtService.extractUserInfo(authHeader).getUserId();
        if(feignClientService.validateId(userId)){
            if (jwtService.extractUserInfo(authHeader).getUserRole().equals(UserRole.COACH)){
                try {
                    Coach coach = coachService.setAchievements(achievements, userId);
                    return new ResponseEntity<>(coach, HttpStatus.OK);
                } catch (IllegalArgumentException e) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/requests")
    public ResponseEntity<List<AssistanceRequest>> getAssistanceRequests(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        String userId = jwtService.extractUserInfo(authHeader).getUserId();
        if(feignClientService.validateId(userId)){
            if (jwtService.extractUserInfo(authHeader).getUserRole().equals(UserRole.COACH)){
                try {
                    List<AssistanceRequest> requests = coachService.getAssistanceRequests(userId);
                    return new ResponseEntity<>(requests, HttpStatus.OK);
                } catch (IllegalArgumentException e) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/requests/approve/{reqId}")
    public ResponseEntity<String> approveRequest(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,@PathVariable("reqId") String reqId) {

        UserInfo userInfo = jwtService.extractUserInfo(authHeader);

        if(feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.COACH)){
                try {
                    String response = coachService.approveRequest(reqId);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (IllegalArgumentException e) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/requests/decline/{reqId}")
    public ResponseEntity<String> declineRequest(@RequestHeader(HttpHeaders.AUTHORIZATION)String authHeader,@PathVariable("reqId") String reqId) {

        UserInfo userInfo = jwtService.extractUserInfo(authHeader);

        if(feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.COACH)){
                String response = coachService.declineRequest(reqId);
                return new ResponseEntity<>(response,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
