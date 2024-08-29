package com.abhijith.eventservice.repo;

import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.Registration;
import com.abhijith.eventservice.model.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,String> {
    List<Registration> findAllByStatus(RegistrationStatus registrationStatus);

    List<Registration> findAllByAthleteId(String athleteId);


    List<Registration> findAllByEventIdAndStatus(Event event, RegistrationStatus registrationStatus);

    Optional<Registration> findByEventIdAndAthleteId(Optional<Event> event, String athleteId);
}
