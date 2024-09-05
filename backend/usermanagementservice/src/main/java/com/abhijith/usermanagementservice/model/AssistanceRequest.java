package com.abhijith.usermanagementservice.model;


import com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator;
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
public class AssistanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ass_req")
    @GenericGenerator(
            name = "ass_req",
            strategy = "com.abhijith.usermanagementservice.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AS_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String assistanceRequestId;
    @OneToOne
    private Athlete athlete;
    private RequestStatus status;
    private String remarks;
    @ManyToOne
    private Coach coach;
}
