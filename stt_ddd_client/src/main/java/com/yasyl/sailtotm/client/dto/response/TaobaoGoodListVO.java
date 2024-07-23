// Response.java

package com.yasyl.sailtotm.client.dto.response;

import com.yasyl.sailtotm.client.dto.common.ItemsDTO;

@lombok.Data
public class TaobaoGoodListVO {
    private String apiInfo;
    private String apiType;
    private long cache;
    private CallArgs callArgs;
    private String clientIp;
    private String error;
    private String errorCode;
    private String executionTime;
    private ItemsDTO items;
    private Language language;
    private String lastId;
    private String reason;
    private String requestId;
    private String secache;
    private String secacheDate;
    private long secacheTime;
    private String serverMemory;
    private String serverTime;
    private String translateStatus;
    private long translateTime;
}

@lombok.Data
class CallArgs {
    private String cat;
    private String endPrice;
    private String page;
    private String q;
    private String startPrice;
}


@lombok.Data
 class Language {
    private String currentLang;
    private String defaultLang;
}