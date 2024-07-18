package com.yasyl.sailtotm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminTopUpDto {
    private String change;
    private String membershipId;
    private String status;
    private String stuffId;
    private String userId;
}