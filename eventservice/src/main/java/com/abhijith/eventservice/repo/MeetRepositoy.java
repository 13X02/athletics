package com.abhijith.eventservice.repo;

import com.abhijith.eventservice.model.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetRepositoy extends JpaRepository<Meet,String> {
}
