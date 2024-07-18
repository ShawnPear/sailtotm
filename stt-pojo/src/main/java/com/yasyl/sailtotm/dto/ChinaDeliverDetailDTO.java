package com.yasyl.sailtotm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChinaDeliverDetailDTO {
    private Integer deliverId;
    private String address;
    private String phoneNumber;
    private String name;
    private String status;
}
