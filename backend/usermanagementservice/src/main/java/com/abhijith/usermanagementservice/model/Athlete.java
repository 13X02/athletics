package com.abhijith.usermanagementservice.model;

import com.abhijith.usermanagementservice.client.Users;
import com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ath_seq")
    @GenericGenerator(
            name = "ath_seq",
            strategy = "com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ATH_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

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

    @CreationTimestamp
    private Date createDate;




}
