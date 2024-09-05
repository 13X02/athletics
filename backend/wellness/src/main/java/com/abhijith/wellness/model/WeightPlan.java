package com.abhijith.wellness.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WeightPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String athleteId;
    private Long startWeight;
    private Long targetWeight;
    private String preference;
    private Long dailyCalorieGoal;
}
