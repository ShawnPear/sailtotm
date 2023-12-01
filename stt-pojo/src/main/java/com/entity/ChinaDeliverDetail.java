package com.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChinaDeliverDetail {
    private Integer deliverId;
    private String address;
    private String phoneNumber;
    private String name;
    private Integer status;
}
