package com.abhijith.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AthleteRequestDto {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private  String height;
    private  String weight;
    private String category;
    private String coachId;
}
