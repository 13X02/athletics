package com.abhijith.usermanagementservice.repository;

import com.abhijith.usermanagementservice.model.AssistanceRequest;
import com.abhijith.usermanagementservice.model.Athlete;
import com.abhijith.usermanagementservice.model.Coach;
import com.abhijith.usermanagementservice.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssistanceRequestRepository extends JpaRepository<AssistanceRequest,String> {
    List<AssistanceRequest> findAllByCoach(Optional<Coach> coach);

    List<AssistanceRequest> findAllByCoachAndStatus(Coach coach, RequestStatus requestStatus);

    boolean existsByAthleteAndCoach(Athlete athlete, Coach coach);
}
