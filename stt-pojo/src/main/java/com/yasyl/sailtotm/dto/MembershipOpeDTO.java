package com.yasyl.sailtotm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipOpeDTO {
    private String change;
    private String membershipid;
    private String status;
    private String stuffid;
    private String userid;
}
