package com.yasyl.sailtotm.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MembershipHistory {
    private Integer historyId;
    private Integer membershipId;
    private Integer userId;
    private Integer statusId;
    private Integer stuffId;
    private Double change;
    private LocalDateTime createdDate;
}
