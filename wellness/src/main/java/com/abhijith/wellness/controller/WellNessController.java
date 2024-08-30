package com.abhijith.wellness.controller;

import com.abhijith.wellness.feign.FeignClientService;
import com.abhijith.wellness.model.DailyDiet;
import com.abhijith.wellness.model.UserInfo;
import com.abhijith.wellness.model.UserRole;
import com.abhijith.wellness.model.WeightPlan;
import com.abhijith.wellness.service.DietService;
import com.abhijith.wellness.service.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wellness")
public class WellNessController {

    @Autowired
    private DietService dietService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FeignClientService feignClientService;

    @PostMapping("/weight-plan")
    public ResponseEntity<WeightPlan> createOrUpdateWeightPlan(@RequestHeader(HttpHeaders.AUTHORIZATION)String authHead, @RequestBody WeightPlan weightPlan) {
        UserInfo userInfo = jwtService.extractUserInfo(authHead);
        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.ATHLETE)){
                String athleteId = feignClientService.findAthleteByUserId(userInfo.getUserId());
                weightPlan.setAthleteId(athleteId);
                return new ResponseEntity<>(dietService.createWeightPlan(weightPlan), HttpStatus.CREATED);

            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(weightPlan, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/daily-diet")
    public ResponseEntity<List<DailyDiet>> getDailyDietByAthleteId(@RequestHeader(HttpHeaders.AUTHORIZATION)String authHead) {

        UserInfo userInfo = jwtService.extractUserInfo(authHead);
        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.ATHLETE)){
                String athleteId = feignClientService.findAthleteByUserId(userInfo.getUserId());
                return new ResponseEntity<>(dietService.getDailyDietListById(athleteId), HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    @PostMapping("/daily-diet")
    public ResponseEntity<DailyDiet> createOrUpdateDailyDiet(@RequestHeader(HttpHeaders.AUTHORIZATION)String authHead,@RequestBody DailyDiet dailyDiet) {
        UserInfo userInfo = jwtService.extractUserInfo(authHead);
        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.ATHLETE)){
                return new ResponseEntity<>(dietService.createOrUpdateDailyDiet(dailyDiet), HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/recommendation")
    public ResponseEntity<String> getAIRecommendedDiet(@RequestHeader(HttpHeaders.AUTHORIZATION)String authHead) {
        UserInfo userInfo = jwtService.extractUserInfo(authHead);
        if (feignClientService.validateId(userInfo.getUserId())){
            if (userInfo.getUserRole().equals(UserRole.ATHLETE)){
                try {
                    String athleteId = feignClientService.findAthleteByUserId(userInfo.getUserId());

                    String recommendation = dietService.getAIRecommendedDiet(athleteId);
                    return new ResponseEntity<>(recommendation, HttpStatus.OK);
                } catch (RuntimeException e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                } catch (JsonProcessingException e) {
                    return   new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
