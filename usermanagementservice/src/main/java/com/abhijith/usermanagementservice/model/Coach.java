package com.abhijith.usermanagementservice.model;

import com.abhijith.usermanagementservice.client.Users;
import com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "co_seq")
    @GenericGenerator(
            name = "co_seq",
            strategy = "com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CO_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
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
