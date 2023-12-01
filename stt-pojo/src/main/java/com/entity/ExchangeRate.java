package com.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeRate {
    private Integer rateId;
    private Double rmb;
    private Double usd;
    private Double manat;
    private String updatedDate;
}
