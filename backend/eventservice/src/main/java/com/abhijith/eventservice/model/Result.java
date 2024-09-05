package com.abhijith.eventservice.model;

import com.abhijith.eventservice.utils.StringPrefixedSequenceIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "res_seq")
    @GenericGenerator(
            name = "res_seq",
            strategy = "com.abhijith.eventservice.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "RES_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String resultId;
    private Double score;
    private String comment;

    @ManyToOne
    private Event event;

    @OneToOne
    private Registration registration;
}
