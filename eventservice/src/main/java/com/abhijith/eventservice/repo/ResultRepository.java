package com.abhijith.eventservice.repo;

import com.abhijith.eventservice.model.Event;
import com.abhijith.eventservice.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result,String> {
    List<Result> findAllByEvent(Event event);
}
