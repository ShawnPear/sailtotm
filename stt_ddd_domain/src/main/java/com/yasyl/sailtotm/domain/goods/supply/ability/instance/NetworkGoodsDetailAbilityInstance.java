package com.yasyl.sailtotm.domain.goods.supply.ability.instance;

import com.yasyl.sailtotm.domain.goods.supply.ability.INetworkGoodsDetailAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import com.yasyl.sailtotm.domain.goods.supply.repository.ISupplyGoodsDetailByThirdAPIRepository;
import com.yasyl.sailtotm.domain.goods.supply.repository.req.GoodDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:19
 **/
@Service
public class NetworkGoodsDetailAbilityInstance implements INetworkGoodsDetailAbility {
    @Autowired
    ISupplyGoodsDetailByThirdAPIRepository supplyGoodsDetailByThirdAPIRepository;

    @Override
    public GoodDetailDO query(String numIid) {
        return supplyGoodsDetailByThirdAPIRepository.queryGoodsDetailByCallAPI(GoodDetailRequest.builder()
                .numIid(numIid)
                .build());
    }
}
