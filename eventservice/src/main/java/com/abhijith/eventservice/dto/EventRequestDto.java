package com.abhijith.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventRequestDto {


    private String eventTitle;

    private Date eventDate;

    private String meetId;

    private String venue;

    private String category;

    private String eventDescription;

}
