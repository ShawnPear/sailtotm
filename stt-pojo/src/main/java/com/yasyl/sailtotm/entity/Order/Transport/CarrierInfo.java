package com.yasyl.sailtotm.entity.Order.Transport;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrierInfo {
    private Integer carrierId;
    private Integer transportId;
    private String carrierNumber;
    private LocalDateTime sendDate;
    private LocalDateTime receiveDate;
}
