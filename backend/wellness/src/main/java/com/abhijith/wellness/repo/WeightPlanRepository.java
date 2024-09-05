package com.abhijith.wellness.repo;

import com.abhijith.wellness.model.WeightPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeightPlanRepository extends JpaRepository<WeightPlan,Long> {
    Optional<WeightPlan> findByAthleteId(String athleteId);
}
