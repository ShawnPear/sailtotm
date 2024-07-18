package com.yasyl.sailtotm.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MembershipVO {
    private String membershipId;
    //    private Integer userId;
    private String balance;
    //    private String password;
    private String supportDate;
}
