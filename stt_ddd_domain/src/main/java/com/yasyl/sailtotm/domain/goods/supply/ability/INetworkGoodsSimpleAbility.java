package com.yasyl.sailtotm.domain.goods.supply.ability;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:37
 **/
public interface INetworkGoodsSimpleAbility {
    List<GoodSimpleDO> search(String numIid, int page, int size);
}
