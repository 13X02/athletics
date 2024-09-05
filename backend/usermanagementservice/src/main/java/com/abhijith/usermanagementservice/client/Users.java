package com.abhijith.usermanagementservice.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users  {


    private String userId;

    private String userName;

    private String password;

    private String email;

    private UserRole role;


}
