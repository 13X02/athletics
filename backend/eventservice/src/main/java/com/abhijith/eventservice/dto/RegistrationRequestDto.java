package com.abhijith.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationRequestDto {

    private String eventId;
    private String athleteId;
}
