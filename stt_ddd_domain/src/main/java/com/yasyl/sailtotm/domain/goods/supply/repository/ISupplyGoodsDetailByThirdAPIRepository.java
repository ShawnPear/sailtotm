package com.yasyl.sailtotm.domain.goods.supply.repository;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import com.yasyl.sailtotm.domain.goods.supply.repository.req.GoodDetailRequest;

/**
 * @program: SailToTm
 * @description: 通过第三方api获取商品详情页
 * @author: wujubin
 * @create: 2024-07-23 19:21
 **/
public interface ISupplyGoodsDetailByThirdAPIRepository {
    GoodDetailDO queryGoodsDetailByCallAPI(GoodDetailRequest goodDetailRequest);
}
