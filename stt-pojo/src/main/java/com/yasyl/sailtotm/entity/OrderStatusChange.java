package com.yasyl.sailtotm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusChange {
    private Integer orderStatusChangeHistoryId;
    private LocalDateTime createdDate;
    private Integer orderId;
    private Integer stuffId;
    private Integer oldStatus;
    private Integer newStatus;
}
