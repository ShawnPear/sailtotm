package com.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChinaDeliverDetailVO {
    private Integer deliverId;
    private String address;
    private String phoneNumber;
    private String name;
    private String status;
}
