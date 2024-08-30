package com.abhijith.eventservice.service;

import com.abhijith.eventservice.dto.RegistrationRequestDto;
import com.abhijith.eventservice.exception.AlreadyRegisteredException;
import com.abhijith.eventservice.exception.AthleteNotFoundException;
import com.abhijith.eventservice.exception.EventNotFoundException;
import com.abhijith.eventservice.exception.RegistrationNotFoundException;
import com.abhijith.eventservice.feign.FeignClientService;
import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.Registration;
import com.abhijith.eventservice.model.RegistrationStatus;
import com.abhijith.eventservice.repo.EventRepository;
import com.abhijith.eventservice.repo.RegistrationRepository;
import com.abhijith.eventservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private FeignClientService feignClientService;

    public Registration save(RegistrationRequestDto registrationRequestDto) {

        if (!feignClientService.validateAthleteId(registrationRequestDto.getAthleteId())){
            throw new AthleteNotFoundException("athlete not found");
        }

        Optional<Event> event = eventRepository.findById(registrationRequestDto.getEventId());
        if (event.isPresent()) {
            Optional<Registration> registration = registrationRepository.findByEventIdAndAthleteId(event,registrationRequestDto.getAthleteId());
            if (registration.isPresent()) {
                throw new AlreadyRegisteredException(registrationRequestDto.getAthleteId());
            }else {
                Registration registration1 = AppUtils.toRegistration(registrationRequestDto, event.get());
                return registrationRepository.save(registration1);

            }
        }else {
            throw new EventNotFoundException(registrationRequestDto.getEventId());
        }

    }

    public List<Registration> findPendingRegistrations() {
        return registrationRepository.findAllByStatus(RegistrationStatus.PENDING);
    }
    public List<Registration> findRegistrationsByAthlete(String athleteId){
        if (!feignClientService.validateAthleteId(athleteId)){
            throw new AthleteNotFoundException("athlete not found");
        }

        return registrationRepository.findAllByAthleteId(athleteId);
    }

    public Registration approveRegistration(String id){
        Optional<Registration> registration = registrationRepository.findById(id);
        if(registration.isPresent()){
            registration.get().setStatus(RegistrationStatus.APPROVED);
            return registrationRepository.save(registration.get());
        }else {
            throw new RegistrationNotFoundException(id);
        }
    }
    public Registration rejectRegistration(String id){
        Optional<Registration> registration = registrationRepository.findById(id);
        if(registration.isPresent()){
            registration.get().setStatus(RegistrationStatus.REJECTED);
            return registrationRepository.save(registration.get());
        }else {
            throw new RegistrationNotFoundException(id);
        }
    }

    public List<Registration> getRegistrationByEvent(String id) {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()) {
            return registrationRepository.findAllByEventIdAndStatus(event.get(), RegistrationStatus.PENDING);
        }else {
            throw new EventNotFoundException(id);
        }

    }
}
