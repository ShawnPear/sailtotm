package com.yasyl.sailtotm.vo;

import lombok.Builder;

@lombok.Data
@Builder
public class ExchangeRateVO {
    private double manat;
    private double rmb;
    private String updatedDate;
    private double usd;
}