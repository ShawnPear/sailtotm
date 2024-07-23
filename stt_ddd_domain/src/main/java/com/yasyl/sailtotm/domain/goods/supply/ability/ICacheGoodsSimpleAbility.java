package com.yasyl.sailtotm.domain.goods.supply.ability;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:37
 **/
public interface ICacheGoodsSimpleAbility {
    GoodSimpleDO search(String numIid);
}
