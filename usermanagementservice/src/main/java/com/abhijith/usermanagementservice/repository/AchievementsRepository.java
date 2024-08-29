package com.abhijith.usermanagementservice.repository;

import com.abhijith.usermanagementservice.model.Achievments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementsRepository extends JpaRepository<Achievments,String> {
}
