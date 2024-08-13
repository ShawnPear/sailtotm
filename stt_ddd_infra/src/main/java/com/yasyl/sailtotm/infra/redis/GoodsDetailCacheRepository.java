package com.yasyl.sailtotm.infra.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import com.yasyl.sailtotm.domain.goods.supply.repository.ISupplyGoodsDetailByCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-08-07 14:35
 **/
@Service
public class GoodsDetailCacheRepository implements ISupplyGoodsDetailByCacheRepository {
    private static final String prefix = "sailtotm:goodsdetail";
    private static final int KEYTIMEOUT_DAY = 1;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    JsonMapper jsonMapper;

    @Override
    public void save(String numIid, GoodDetailDO goodDetailDO) {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(prefix + ":" + numIid, goodDetailDO);
        redisTemplate.expire(prefix + ":" + numIid, KEYTIMEOUT_DAY, TimeUnit.DAYS);
    }

    @Override
    public GoodDetailDO query(String numIid) throws JsonProcessingException {
        ValueOperations ops = redisTemplate.opsForValue();
        String jsonGoodDetail = (String) ops.get(prefix + ":" + numIid);
        if (jsonGoodDetail == null) {
            return null;
        }
        GoodDetailDO goodDetailDO = jsonMapper.readValue(jsonGoodDetail, GoodDetailDO.class);
        return goodDetailDO;
    }
}
