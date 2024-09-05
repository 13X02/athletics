package com.abhijith.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssistanceRequestDto {

    private String coachId;
    private String remarks;
}
