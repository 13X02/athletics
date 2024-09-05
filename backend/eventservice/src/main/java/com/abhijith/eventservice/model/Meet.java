package com.abhijith.eventservice.model;

import com.abhijith.eventservice.utils.StringPrefixedSequenceIdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reg_seq")
    @GenericGenerator(
            name = "reg_seq",
            strategy = "com.abhijith.eventservice.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "M_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String meetId;
    private String meetName;

    private String photoUrl;
}
