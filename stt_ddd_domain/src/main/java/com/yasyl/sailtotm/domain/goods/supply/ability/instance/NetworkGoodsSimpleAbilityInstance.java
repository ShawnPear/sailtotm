package com.yasyl.sailtotm.domain.goods.supply.ability.instance;

import com.yasyl.sailtotm.domain.goods.supply.ability.INetworkGoodsSimpleAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:38
 **/
@Service
public class NetworkGoodsSimpleAbilityInstance implements INetworkGoodsSimpleAbility {
    @Override
    public List<GoodSimpleDO> search(String numIid, int page, int size) {
        return null;
    }
}
