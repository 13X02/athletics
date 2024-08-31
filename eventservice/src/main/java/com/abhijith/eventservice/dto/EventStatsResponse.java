package com.abhijith.eventservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventStatsResponse {
    private Long totalEvents;
    private Map<String, Integer> dailyEventCounts; // Key: Date, Value: Count of events on that date
}
