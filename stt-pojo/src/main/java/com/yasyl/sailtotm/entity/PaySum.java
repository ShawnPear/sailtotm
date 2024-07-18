package com.yasyl.sailtotm.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaySum {
    private Integer paySumId;
    private Double payNow;
    private Double payExpect;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
