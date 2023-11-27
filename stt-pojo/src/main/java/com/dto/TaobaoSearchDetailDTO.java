package com.dto;

import lombok.Builder;

@lombok.Data
@Builder
public class TaobaoSearchDetailDTO {
    private String isPromotion;
    private String key;
    private String lang;
    private String numIid;
    private String secret;
}