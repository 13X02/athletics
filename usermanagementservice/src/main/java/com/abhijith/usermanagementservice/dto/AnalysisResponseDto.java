package com.abhijith.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisResponseDto {
    private long totalAthletes;
    private long totalCoaches;
    private Map<Date, Long> athletesCreatedPerDay;
    private Map<Date, Long> coachesCreatedPerDay;

    // Constructors, Getters, and Setters
}
