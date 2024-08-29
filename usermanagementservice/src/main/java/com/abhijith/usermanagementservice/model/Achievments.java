package com.abhijith.usermanagementservice.model;

import jakarta.persistence.Entity;
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
    private String achievmentId;
    private String meetName;
    private String event;
    private String score;
    private String perfomance;
}
