package com.abhijith.usermanagementservice.repository;

import com.abhijith.usermanagementservice.model.Coach;
import com.abhijith.usermanagementservice.client.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach,String> {
    Optional<Coach> findByUserId(String userId);
    @Query("SELECT c.createDate, COUNT(c) FROM Coach c GROUP BY c.createDate")
    List<Object[]> getCoachesGroupedByCreateDate();

    List<Coach> findAllByFirstNameContainingIgnoreCase(String name);
}
