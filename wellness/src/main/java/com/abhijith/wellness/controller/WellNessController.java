package com.abhijith.wellness.controller;

import com.abhijith.wellness.model.DailyDiet;
import com.abhijith.wellness.model.WeightPlan;
import com.abhijith.wellness.service.DietService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wellness")
public class WellNessController {

    @Autowired
    private DietService dietService;

    @PostMapping("/weight-plan")
    public ResponseEntity<WeightPlan> createOrUpdateWeightPlan(@RequestBody WeightPlan weightPlan) {
        WeightPlan updatedPlan = dietService.createWeightPlan(weightPlan);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    @GetMapping("/daily-diet/{athleteId}")
    public ResponseEntity<List<DailyDiet>> getDailyDietByAthleteId(@PathVariable String athleteId) {
        List<DailyDiet> dailyDiets = dietService.getDailyDietListById(athleteId);
        if (dailyDiets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dailyDiets, HttpStatus.OK);
    }

    @PostMapping("/daily-diet")
    public ResponseEntity<DailyDiet> createOrUpdateDailyDiet(@RequestBody DailyDiet dailyDiet) {
        DailyDiet updatedDiet = dietService.createOrUpdateDailyDiet(dailyDiet);
        return new ResponseEntity<>(updatedDiet, HttpStatus.OK);
    }

    @GetMapping("/recommendation/{athleteId}")
    public ResponseEntity<String> getAIRecommendedDiet(@PathVariable String athleteId) {
        try {
            String recommendation = dietService.getAIRecommendedDiet(athleteId);
            return new ResponseEntity<>(recommendation, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException e) {
            return   new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
