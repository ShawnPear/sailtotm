package com.yasyl.sailtotm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MembershipPayDTO {
    private String userId;
    private String membershipId;
    private String password;
    private String actualPay;
    private String defaultStuff;
}
