package com.yasyl.sailtotm.domain.goods.supply.ability;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 21:31
 **/
public interface INetworkGoodsDetailAbility {
    GoodDetailDO query(String numIid);
}
