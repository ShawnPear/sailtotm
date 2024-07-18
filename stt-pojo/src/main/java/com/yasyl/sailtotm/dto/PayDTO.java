package com.yasyl.sailtotm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO {
    private String orderId;
    private String password;
    private String membershipId;
    private String userId;
    private String stuffId;
    private String actualPay;
    private String payType;
}
