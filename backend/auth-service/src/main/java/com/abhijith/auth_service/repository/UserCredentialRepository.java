package com.abhijith.auth_service.repository;

import com.abhijith.auth_service.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,String> {

    Optional<UserCredential> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
