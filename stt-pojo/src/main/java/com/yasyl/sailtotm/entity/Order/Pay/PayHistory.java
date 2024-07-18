package com.yasyl.sailtotm.entity.Order.Pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayHistory {
    private Integer payId;
    private LocalDateTime createdDate;
    private Double payAmount;
    private Integer payType;
    private Integer stuffId;
    private LocalDateTime updatedDate;
    private Integer paySumId;
    private Integer payOutOrIn;
}
