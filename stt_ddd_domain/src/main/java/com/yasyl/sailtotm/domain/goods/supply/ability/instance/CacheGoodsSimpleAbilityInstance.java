package com.yasyl.sailtotm.domain.goods.supply.ability.instance;

import com.yasyl.sailtotm.domain.goods.supply.ability.ICacheGoodsSimpleAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:38
 **/
@Service
public class CacheGoodsSimpleAbilityInstance implements ICacheGoodsSimpleAbility {

    @Qualifier("goodsCacheSyncThreadPool")
    @Autowired
    private ThreadPoolTaskExecutor goodsCacheSyncThreadPool;

    @Override
    public List<GoodSimpleDO> search(String key,int page,int size) {
        return Collections.emptyList();
    }

    @Override
    public void sync(String key, List<GoodSimpleDO> goodSimpleDO) {

    }
}
