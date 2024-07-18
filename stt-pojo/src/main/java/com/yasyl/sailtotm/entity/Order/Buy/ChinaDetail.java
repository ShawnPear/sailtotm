package com.yasyl.sailtotm.entity.Order.Buy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChinaDetail {
    private Integer chinaDetailId;
    private Integer deliverId;
    private Integer stuffId;
    private String taobaoOrderId;
    private LocalDateTime sendDate;
    private LocalDateTime receiveDate;
    private LocalDateTime purchaseDate;
    private Double paySum;
    private String taobaoAccount;
}
