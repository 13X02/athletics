package com.abhijith.usermanagementservice.controller;

import com.abhijith.usermanagementservice.dto.AthleteRequestDto;
import com.abhijith.usermanagementservice.model.Athlete;
import com.abhijith.usermanagementservice.service.AthleteService;  // Updated to service package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @PostMapping("/create")
    public ResponseEntity<String> createProfile(@RequestBody AthleteRequestDto athleteRequestDto) {
//        Athlete athlete = athleteService.createProfile(athleteRequestDto);
        return new ResponseEntity<>("Createddd", HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Athlete> getAthleteById(@PathVariable String id) {
//        Athlete athlete = athleteService.getAthleteById(id);
//        if (athlete != null) {
//            return new ResponseEntity<>(athlete, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Athlete>> getAllAthletes() {
//        List<Athlete> athletes = athleteService.getAll();
//        return new ResponseEntity<>(athletes, HttpStatus.OK);
//    }
//
//    @PutMapping("/edit")
//    public ResponseEntity<Athlete> editAthlete( @RequestBody AthleteRequestDto athleteRequestDto) {
//
//        Athlete updatedAthlete = athleteService.editAthlete(athleteRequestDto);
//        return new ResponseEntity<>(updatedAthlete, HttpStatus.OK);
//    }
}
