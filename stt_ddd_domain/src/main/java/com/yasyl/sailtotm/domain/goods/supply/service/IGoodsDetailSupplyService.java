package com.yasyl.sailtotm.domain.goods.supply.service;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodQueryEnum;
import com.yasyl.sailtotm.domain.goods.supply.model.response.GoodDetailResponse;

import java.util.List;

/**
 * @program: SailToTm
 * @description: 商品供给Service，可以通过Redis缓存或第三方api获取
 * @author: wujubin
 * @create: 2024-07-23 19:20
 **/
public interface IGoodsDetailSupplyService {
    GoodDetailResponse QueryGoodsDetailByProductId(String numIid, GoodQueryEnum mode);

    List<GoodDetailResponse> batchQueryGoodsDetailByProductId(List<String> numIidList, GoodQueryEnum mode);
}
