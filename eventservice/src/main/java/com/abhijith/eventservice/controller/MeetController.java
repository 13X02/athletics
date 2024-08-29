package com.abhijith.eventservice.controller;

import com.abhijith.eventservice.dto.MeetRequestDto;
import com.abhijith.eventservice.model.Meet;
import com.abhijith.eventservice.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meet")
public class MeetController {

    @Autowired
    private MeetService meetService;

    @PostMapping
    public ResponseEntity<Meet> createMeet(@RequestBody MeetRequestDto meetRequestDto) {

        return new ResponseEntity<>(meetService.createMeet(meetRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Meet>> getMeetList() {

        return ResponseEntity.ok(meetService.getMeetList());
    }
}
