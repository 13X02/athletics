package com.abhijith.usermanagementservice.model;

import com.abhijith.usermanagementservice.client.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String athleteId;

    @JsonIgnore
    private String userId;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private  String height;
    private  String weight;
    private String category;

    @OneToOne
    private Coach coach;

    private String photoUrl;




}
