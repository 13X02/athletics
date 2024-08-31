package com.abhijith.usermanagementservice.model;

import com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator;
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
public class Achievments {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achive_seq")
    @GenericGenerator(
            name = "achive_seq",
            strategy = "com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AC_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String achievmentId;
    private String meetName;
    private String event;
    private String score;
    private String perfomance;
}
