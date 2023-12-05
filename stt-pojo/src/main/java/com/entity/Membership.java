package com.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Membership {
    private Integer membershipId;
    private Integer userId;
    private Double balance;
    private String password;
    private LocalDateTime createdDate;
}
