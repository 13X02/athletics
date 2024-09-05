package com.abhijith.usermanagementservice.dto;

import com.abhijith.usermanagementservice.client.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String userId;
    private UserRole userRole;
}
