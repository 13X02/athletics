package com.abhijith.usermanagementservice.repository;

import com.abhijith.usermanagementservice.model.Athlete;
import com.abhijith.usermanagementservice.client.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete,String> {
    Optional<Athlete> findByUserId(String userId);
    @Query("SELECT a.createDate, COUNT(a) FROM Athlete a GROUP BY a.createDate")
    List<Object[]> getAthletesGroupedByCreateDate();
    boolean existsByUserId(String userId);
}
