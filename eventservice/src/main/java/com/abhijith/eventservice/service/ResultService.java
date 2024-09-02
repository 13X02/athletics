package com.abhijith.eventservice.service;

import com.abhijith.eventservice.client.Athlete;
import com.abhijith.eventservice.dto.ResultAthleteResponse;
import com.abhijith.eventservice.dto.ResultEventResponse;
import com.abhijith.eventservice.dto.ResultRequestDto;
import com.abhijith.eventservice.exception.AthleteNotFoundException;
import com.abhijith.eventservice.exception.EventNotFoundException;
import com.abhijith.eventservice.exception.RegistrationNotFoundException;
import com.abhijith.eventservice.feign.FeignClientService;
import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.EventStatus;
import com.abhijith.eventservice.model.Registration;
import com.abhijith.eventservice.model.Result;
import com.abhijith.eventservice.repo.EventRepository;
import com.abhijith.eventservice.repo.RegistrationRepository;
import com.abhijith.eventservice.repo.ResultRepository;
import com.abhijith.eventservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private FeignClientService feignClientService;

    public List<Result> createResults(List<ResultRequestDto> resultRequestDtos) {
        List<Result> results = new ArrayList<>();

        for (ResultRequestDto resultRequestDto : resultRequestDtos) {
            Optional<Event> event = eventRepository.findById(resultRequestDto.getEventId());
            if (event.isPresent()) {
                event.get().setStatus(EventStatus.COMPLETED);
                eventRepository.save(event.get());
                Optional<Registration> registration = registrationRepository.findById(resultRequestDto.getRegId());
                if (registration.isPresent()) {
                    Result result = AppUtils.toResult(resultRequestDto, event.get(), registration.get());
                    results.add(result);
                } else {
                    throw new RegistrationNotFoundException(resultRequestDto.getRegId());
                }
            } else {
                throw new EventNotFoundException(resultRequestDto.getEventId());
            }
        }

        // Save all results at once
        return resultRepository.saveAll(results);
    }


    public List<Event> getAllResults() {
        return resultRepository.findAll().stream()
                .map(result -> result.getRegistration().getEvent())  // Navigate through Registration to get Event
                .distinct()  // Ensure unique events if needed
                .collect(Collectors.toList());  // Collect the results into a list
    }


    public List<ResultAthleteResponse> findResultByEventId(String eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            throw new EventNotFoundException(eventId);
        }

        Event event = eventOptional.get();
        List<Result> results = resultRepository.findAllByEvent(event);

        return results.stream().map(result -> {
            Registration registration = result.getRegistration();
            Athlete athlete = feignClientService.getAthleteById(registration.getAthleteId()).getBody();
            return new ResultAthleteResponse(athlete, event, result.getScore(), result.getComment());
        }).collect(Collectors.toList());
    }


    public List<Result> findResultByAthleteId(String athleteId) {
        if (!feignClientService.validateAthleteId(athleteId)){
            throw new AthleteNotFoundException("athlete not found");
        }

        List<Result> results = resultRepository.findAll().stream().filter(e->e.getRegistration().getAthleteId().equals(athleteId)).toList();
        return results;

    }

    public List<Result> findResultByUserId(String userId) {
        String athleteId =feignClientService.findAthleteIdByUserId(userId);
        System.out.println(athleteId);
        if(athleteId == null){
            throw new AthleteNotFoundException("athlete not found");
        }
        return findResultByAthleteId(athleteId);
    }
}
