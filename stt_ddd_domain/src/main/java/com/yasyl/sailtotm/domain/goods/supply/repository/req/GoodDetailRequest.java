package com.yasyl.sailtotm.domain.goods.supply.repository.req;

import lombok.Builder;

@lombok.Data
@Builder
public class GoodDetailRequest {
    private String isPromotion;
    private String key;
    private String lang;
    private String numIid;
    private String secret;
}