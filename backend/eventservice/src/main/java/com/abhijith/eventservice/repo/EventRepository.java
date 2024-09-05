package com.abhijith.eventservice.repo;

import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {

    @Query("SELECT e.createdAt, COUNT(e) FROM Event e GROUP BY e.createdAt")
    List<Object[]> countEventsByDate();

    List<Event> findAllByStatus(EventStatus eventStatus);
}
