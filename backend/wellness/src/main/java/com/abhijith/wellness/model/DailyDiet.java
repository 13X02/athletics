package com.abhijith.wellness.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyDiet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String athleteId;
    private Date date;
    private Long calories;
    private Long currentWeight;

    @ManyToOne
    private WeightPlan weightPlan;


}
