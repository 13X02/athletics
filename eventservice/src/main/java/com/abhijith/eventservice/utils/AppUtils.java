package com.abhijith.eventservice.utils;

import com.abhijith.eventservice.dto.EventRequestDto;
import com.abhijith.eventservice.dto.EventResponseDto;
import com.abhijith.eventservice.dto.RegistrationRequestDto;
import com.abhijith.eventservice.dto.ResultRequestDto;
import com.abhijith.eventservice.model.*;

public class AppUtils {

    // Event Mappers

    public static EventResponseDto toEventResponse(Event event){
        if (event == null) {
            return null;
        }

        return new EventResponseDto(
                event.getEventId(),

                event.getEventTitle(),
                event.getEventDate(),
                event.getMeet().getMeetName(),
                event.getCategory()
        );
    }

    public static Result toResult(ResultRequestDto resultRequestDto,Event event,Registration registration){
        if (resultRequestDto == null) {
            return null;
        }

        return new Result(
                null,  // ID is auto-generated, so it should be null
                resultRequestDto.getScore(),
                resultRequestDto.getComment(),
                event,
                registration

        );
    }

    public static Event toEvent(EventRequestDto eventRequestDto, Meet meet) {
        if (eventRequestDto == null) {
            return null;
        }



        return new Event(
                null,  // ID is auto-generated, so it should be null
                eventRequestDto.getEventDate(),
                eventRequestDto.getEventTitle(),
                meet,
                eventRequestDto.getVenue(),
                eventRequestDto.getCategory(),
                eventRequestDto.getEventDescription()
        );
    }

    public static EventRequestDto toEventRequestDto(Event event) {
        if (event == null) {
            return null;
        }

        return new EventRequestDto(
                event.getEventTitle(),
                event.getEventDate(),
                event.getMeet().getMeetName(),
                event.getVenue(),
                event.getCategory(),
                event.getEventDescription()
        );
    }

    // Registration Mappers
    public static Registration toRegistration(RegistrationRequestDto registrationRequestDto, Event event) {
        if (registrationRequestDto == null || event == null) {
            return null;
        }

        return new Registration(
                null,  // ID is auto-generated, so it should be null
                registrationRequestDto.getAthleteId(),
                RegistrationStatus.PENDING,
                event
        );
    }

    public static RegistrationRequestDto toRegistrationRequestDto(Registration registration) {
        if (registration == null) {
            return null;
        }

        return new RegistrationRequestDto(
                registration.getEventId().getEventId(),
                registration.getAthleteId()
        );
    }
}
