package com.abhijith.usermanagementservice.service;

import com.abhijith.usermanagementservice.client.UserRole;
import com.abhijith.usermanagementservice.dto.UserInfo;
import com.abhijith.usermanagementservice.exception.AuthorizationFailException;
import com.abhijith.usermanagementservice.model.RequestStatus;
import com.abhijith.usermanagementservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    private JwtUtil jwtUtil;

    public UserInfo extractUserInfo(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                jwtUtil.validateToken(token);
                String userId = jwtUtil.getUserIdFromToken(token);
                String role = jwtUtil.getRoleFromToken(token);
                // Use userId and role as needed
                return new UserInfo(userId, UserRole.valueOf(role));
            } catch (Exception e) {
                throw new AuthorizationFailException("Invalid token");
            }
        }
        throw new AuthorizationFailException("Missing or invalid authorization header");
    }
}
