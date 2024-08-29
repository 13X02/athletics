package com.abhijith.usermanagementservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AssistanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String assistanceRequestId;
    @OneToOne
    private Athlete athlete;
    private RequestStatus status;
    private String remarks;
    @ManyToOne
    private Coach coach;
}
