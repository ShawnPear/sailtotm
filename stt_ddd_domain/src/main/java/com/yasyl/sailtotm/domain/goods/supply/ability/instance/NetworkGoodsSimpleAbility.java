package com.yasyl.sailtotm.domain.goods.supply.ability.instance;

import com.yasyl.sailtotm.domain.goods.supply.ability.INetworkGoodsSimpleAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;
import org.springframework.stereotype.Service;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:38
 **/
@Service
public class NetworkGoodsSimpleAbility implements INetworkGoodsSimpleAbility {
    @Override
    public GoodSimpleDO search(String numIid) {
        return null;
    }
}
