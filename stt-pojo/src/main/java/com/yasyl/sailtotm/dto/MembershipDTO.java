package com.yasyl.sailtotm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDTO {
//    private Integer membershipId;
    private String userId;
//    private Double balance;
    private String password;
//    private LocalDateTime createdDate;
}
