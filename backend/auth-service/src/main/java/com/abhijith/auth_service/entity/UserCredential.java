package com.abhijith.auth_service.entity;

import com.abhijith.auth_service.util.StringPrefixedSequenceIdGenerator;
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
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "res_seq")
    @GenericGenerator(
            name = "res_seq",
            strategy = "com.abhijith.auth_service.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "U_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private UserRole role;

}
