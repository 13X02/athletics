package com.abhijith.eventservice.model;

import com.abhijith.eventservice.utils.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reg_seq")
    @GenericGenerator(
            name = "reg_seq",
            strategy = "com.abhijith.eventservice.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "R_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })    private String registrationId;
    private String athleteId;
    private RegistrationStatus status;
    @ManyToOne
    private Event event;
}
