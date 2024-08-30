package com.abhijith.usermanagementservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Achievments {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String achievmentId;
    private String meetName;
    private String event;
    private String score;
    private String perfomance;
}
