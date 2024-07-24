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
    private String numIid;
    private String detailUrl;
    private String picUrl;
    private String price;
    private String promotionPrice;
    private long sales;
    private String sellerNick;
    private String title;
    private String sellerNickZh;
    private String titleZh;

    public static GoodSimpleDO buildFromDetail(GoodDetailDO goodDetailDO) {
        GoodSimpleDO simpleDO = new GoodSimpleDO();

        simpleDO.setNumIid(goodDetailDO.getNumIid());
        simpleDO.setDetailUrl(goodDetailDO.getDetailUrl());
        simpleDO.setSales(goodDetailDO.getSales());
        simpleDO.setPicUrl(goodDetailDO.getPicUrl());
        simpleDO.setPrice(goodDetailDO.getOrginalPrice());
        simpleDO.setPromotionPrice(goodDetailDO.getPrice());
        simpleDO.setSellerNick(goodDetailDO.getNick());
        simpleDO.setTitle(goodDetailDO.getTitle());
        simpleDO.setSellerNickZh(goodDetailDO.getNickZh());
        simpleDO.setTitleZh(goodDetailDO.getTitleZh());
        
        return simpleDO;
    }
}