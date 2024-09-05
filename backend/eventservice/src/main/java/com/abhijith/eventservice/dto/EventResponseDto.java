package com.abhijith.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
    private String eventId;
    private String eventTitle;

    private Date eventDate;

    private String meetName;

    private String category;

    private String photoUrl;

}
