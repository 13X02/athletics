package com.abhijith.auth_service.service;

import com.abhijith.auth_service.entity.UserCredential;
import com.abhijith.auth_service.exception.UserExistException;
import com.abhijith.auth_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public UserCredential saveUser(UserCredential credential) {

        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        try{
            return repository.save(credential);

        }catch (Exception e){
            throw new UserExistException("User credential already exist");
        }

    }

    public String generateToken(String username , String userId ,String role) {
        return jwtService.generateToken(username,userId,role);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


    public Boolean validateId(String id) {
        return repository.existsById(id);
    }
}
