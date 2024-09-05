package com.abhijith.eventservice.controller;

import com.abhijith.eventservice.dto.MeetRequestDto;
import com.abhijith.eventservice.dto.UserInfo;
import com.abhijith.eventservice.dto.UserRole;
import com.abhijith.eventservice.feign.FeignClientService;
import com.abhijith.eventservice.model.Meet;
import com.abhijith.eventservice.service.JwtService;
import com.abhijith.eventservice.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/meet")
public class MeetController {

    @Autowired
    private MeetService meetService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FeignClientService feignClientService;

    @PostMapping("/create")
    public ResponseEntity<Meet> createMeet(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHead,
                                           @RequestParam("meetName") String meetName,
                                           @RequestParam("photo") MultipartFile photo) throws IOException {

        // Extract user info from the JWT
        UserInfo userInfo = jwtService.extractUserInfo(authHead);

        // Validate user ID and check for ADMIN role
        if (feignClientService.validateId(userInfo.getUserId())) {
            if (userInfo.getRole().equals(UserRole.ADMIN)) {
                // Create the MeetRequestDto from the request parameters
                MeetRequestDto meetRequestDto = new MeetRequestDto(meetName);

                // Call the service method to create the meet, including the photo
                Meet createdMeet = meetService.createMeet(meetRequestDto, photo);
                return new ResponseEntity<>(createdMeet, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Meet>> getMeetList() {

        return ResponseEntity.ok(meetService.getMeetList());
    }
}
