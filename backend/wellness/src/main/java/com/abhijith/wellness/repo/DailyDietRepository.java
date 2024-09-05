package com.abhijith.wellness.repo;

import com.abhijith.wellness.model.DailyDiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyDietRepository extends JpaRepository<DailyDiet,Long> {
    List<DailyDiet> findAllByAthleteId(String athleteId);
}
