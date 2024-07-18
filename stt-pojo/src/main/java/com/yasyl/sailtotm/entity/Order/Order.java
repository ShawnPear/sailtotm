package com.yasyl.sailtotm.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private Integer userId;
    private Integer goodDetailId;
    private Integer transportDetailId;
    private Integer chinaDetailId;
    private Integer paySumId;
    private Integer statusId;
    private Integer pickupCode;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
