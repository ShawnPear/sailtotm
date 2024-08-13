package com.yasyl.sailtotm.domain.goods.supply.ability.instance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yasyl.sailtotm.common.constant.MessageConstant;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.domain.goods.supply.ability.ICacheGoodsDetailAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import com.yasyl.sailtotm.domain.goods.supply.repository.ISupplyGoodsDetailByCacheRepository;
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
    
    @Autowired
    ISupplyGoodsDetailByCacheRepository supplyGoodsDetailByCacheRepository;
    
    @Override
    public void store(String numIid, GoodDetailDO goodDetailDO) {
        supplyGoodsDetailByCacheRepository.save(numIid,goodDetailDO);
    }

    @Override
    public GoodDetailDO query(String numIid) throws JsonProcessingException,RedisException {
        GoodDetailDO detailDO = null;
        detailDO = supplyGoodsDetailByCacheRepository.query(numIid);
        if(detailDO == null){
            throw new RedisException(MessageConstant.FAIL);
        }
        return detailDO;
    }
}
