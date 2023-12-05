package com.entity;

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
    private Double paySum;
    private Integer payType;
    private Integer stuffId;
}
