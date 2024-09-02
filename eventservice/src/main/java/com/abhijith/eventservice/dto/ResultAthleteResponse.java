package com.abhijith.eventservice.dto;

import com.abhijith.eventservice.client.Athlete;
import com.abhijith.eventservice.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultAthleteResponse {

    private Athlete athlete;
    private Event event;
    private Double score;
    private String comment;
}
