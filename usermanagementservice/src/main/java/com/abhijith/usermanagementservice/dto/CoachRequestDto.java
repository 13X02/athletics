package com.abhijith.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachRequestDto {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String category;
}
