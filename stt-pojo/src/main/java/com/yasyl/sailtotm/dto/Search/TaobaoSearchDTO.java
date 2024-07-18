package com.yasyl.sailtotm.dto.Search;

import lombok.Builder;

@lombok.Data
@Builder
public class TaobaoSearchDTO {
    private String cat;
    private String endPrice;
    private String key;
    private String page;
    private String q;
    private String secret;
    private String sort;
    private String startPrice;
}
