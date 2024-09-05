package com.abhijith.eventservice.dto;

import com.abhijith.eventservice.model.Event;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultRequestDto {

    private Double score;
    private String comment;

    private String eventId;

    private String regId;

}
