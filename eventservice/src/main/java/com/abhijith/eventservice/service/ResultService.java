package com.abhijith.eventservice.service;

import com.abhijith.eventservice.dto.ResultRequestDto;
import com.abhijith.eventservice.exception.EventNotFoundException;
import com.abhijith.eventservice.exception.RegistrationNotFoundException;
import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.Registration;
import com.abhijith.eventservice.model.Result;
import com.abhijith.eventservice.repo.EventRepository;
import com.abhijith.eventservice.repo.RegistrationRepository;
import com.abhijith.eventservice.repo.ResultRepository;
import com.abhijith.eventservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;


    public Result createResult(ResultRequestDto resultRequestDto){

        Optional<Event> event = eventRepository.findById(resultRequestDto.getEventId());
        if(event.isPresent()){
            Optional<Registration> registration = registrationRepository.findById(resultRequestDto.getRegId());
            if(registration.isPresent()){
                Result result = AppUtils.toResult(resultRequestDto,event.get(),registration.get());
                return resultRepository.save(result);
            }else{
                throw new RegistrationNotFoundException(resultRequestDto.getRegId());
            }

        }else {
            throw new EventNotFoundException(resultRequestDto.getEventId());
        }
    }

    public List<Result> getAllResults(){
        return resultRepository.findAll();
    }

    public List<Result> findResultByEventId(String eventId){
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            return resultRepository.findAllByEvent(event.get());
        } else {
            throw new EventNotFoundException(eventId);
        }
    }


}
