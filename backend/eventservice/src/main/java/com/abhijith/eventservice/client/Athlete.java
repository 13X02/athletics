package com.abhijith.eventservice.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Athlete {


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



    private String photoUrl;

    @CreationTimestamp
    private Date createDate;




}
