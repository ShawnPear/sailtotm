// Response.java

package com.vo.TaobaoGood;

import com.entity.TaobaoGoodList.CallArgs;
import com.entity.TaobaoGoodList.Items;
import com.entity.TaobaoGoodList.Language;

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
    private Items items;
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
