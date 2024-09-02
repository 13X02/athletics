package com.abhijith.eventservice.service;

import com.abhijith.eventservice.dto.EventRequestDto;
import com.abhijith.eventservice.dto.EventStatsResponse;
import com.abhijith.eventservice.exception.EventNotFoundException;
import com.abhijith.eventservice.exception.MeetNotFoundException;
import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.EventStatus;
import com.abhijith.eventservice.model.Meet;
import com.abhijith.eventservice.repo.EventRepository;
import com.abhijith.eventservice.repo.MeetRepositoy;
import com.abhijith.eventservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MeetRepositoy meetRepositoy;

    @Autowired
    private AwsService awsService;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(EventRequestDto eventRequestDto, MultipartFile photo) throws IOException {

        // Find the Meet entity
        Optional<Meet> meet = meetRepositoy.findById(eventRequestDto.getMeetId());
        if (meet.isPresent()) {
            // Upload the photo to AWS S3
            String photoUrl = awsService.uploadFile(photo.getInputStream(), photo.getOriginalFilename());

            // Create the Event entity with the photo URL
            Event event = AppUtils.toEvent(eventRequestDto, meet.get(),photoUrl);

            // Save and return the event
            return eventRepository.save(event);
        } else {
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

    public EventStatsResponse getEventStatistics() {
        // Get total count of events
        Long totalEvents = eventRepository.count();

        // Get daily event counts
        List<Object[]> eventCounts = eventRepository.countEventsByDate();
        Map<String, Integer> dailyEventCounts = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Object[] record : eventCounts) {
            Date eventDate = (Date) record[0];
            Long count = (Long) record[1];
            dailyEventCounts.put(dateFormat.format(eventDate), count.intValue());
        }

        return new EventStatsResponse(totalEvents, dailyEventCounts);
    }

    public List<Event> getInProgressEvents() {

        return eventRepository.findAllByStatus(EventStatus.STARTED);
    }
}
