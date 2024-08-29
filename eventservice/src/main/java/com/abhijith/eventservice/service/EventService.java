package com.abhijith.eventservice.service;

import com.abhijith.eventservice.dto.EventRequestDto;
import com.abhijith.eventservice.exception.EventNotFoundException;
import com.abhijith.eventservice.exception.MeetNotFoundException;
import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.Meet;
import com.abhijith.eventservice.repo.EventRepository;
import com.abhijith.eventservice.repo.MeetRepositoy;
import com.abhijith.eventservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MeetRepositoy meetRepositoy;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(EventRequestDto eventRequestDto) {

        Optional<Meet> meet = meetRepositoy.findById(eventRequestDto.getMeetId());
        if (meet.isPresent()){

            Event event = AppUtils.toEvent(eventRequestDto,meet.get());
            return eventRepository.save(event);
        }else{
            throw new MeetNotFoundException(eventRequestDto.getMeetId());
        }


    }
    public Event getEventById(String id) {

        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        }else {
            throw new EventNotFoundException(id);
        }

    }
}
