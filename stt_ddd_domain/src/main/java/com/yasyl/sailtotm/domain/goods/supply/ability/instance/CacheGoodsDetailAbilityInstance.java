package com.yasyl.sailtotm.domain.goods.supply.ability.instance;

import com.yasyl.sailtotm.domain.goods.supply.ability.ICacheGoodsDetailAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:20
 **/
@Service
public class CacheGoodsDetailAbilityInstance implements ICacheGoodsDetailAbility {
    @Qualifier("goodsCacheSyncThreadPool")
    @Autowired
    private ThreadPoolTaskExecutor goodsCacheSyncThreadPool;
    
    @Override
    public void store(String numIid, GoodDetailDO goodDetailDO) {

    }

    @Override
    public GoodDetailDO query(String numIid) {
        return null;
    }
}
