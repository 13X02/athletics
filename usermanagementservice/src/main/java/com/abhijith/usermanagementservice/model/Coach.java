package com.abhijith.usermanagementservice.model;

import com.abhijith.usermanagementservice.client.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String coachId;

    private String userId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String category;

    @OneToMany
    private List<Achievments> achievements;



    @OneToMany
    @JsonIgnore
    private List<Athlete> athletes;

    private String photoUrl;


}
