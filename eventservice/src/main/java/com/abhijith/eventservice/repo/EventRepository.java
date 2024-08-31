package com.abhijith.eventservice.repo;

import com.abhijith.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {

    @Query("SELECT e.createdDate, COUNT(e) FROM Event e GROUP BY e.createdDate")
    List<Object[]> countEventsByDate();
}
