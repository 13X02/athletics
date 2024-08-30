package com.abhijith.usermanagementservice.repository;

import com.abhijith.usermanagementservice.model.Athlete;
import com.abhijith.usermanagementservice.client.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete,String> {
    Optional<Athlete> findByUserId(String userId);

}
