package com.yasyl.sailtotm.domain.goods.supply.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author shawn
 */
@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodSimpleDO {
    private String detailUrl;
    private String numIid;
    private String picUrl;
    private String price;
    private String promotionPrice;
    private long sales;
    private String sellerNick;
    private String title;
    private String sellerNickZh;
    private String titleZh;
}