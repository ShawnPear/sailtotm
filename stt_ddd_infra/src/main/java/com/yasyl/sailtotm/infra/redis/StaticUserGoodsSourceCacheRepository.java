package com.yasyl.sailtotm.infra.redis;

import com.yasyl.sailtotm.common.enums.GoodSourceEnum;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.domain.userpreference.repository.cache.IStaticUserGoodsSourceCacheRepository;
import com.yasyl.sailtotm.infra.redis.converter.UserGoodsSourceCacheConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-20 10:08
 **/
@Service
public class StaticUserGoodsSourceCacheRepository implements IStaticUserGoodsSourceCacheRepository {
    private static final String prefix = "sailtotm:usergoodssource";
    private static final String listPrefix = prefix + ":" + "list";
    private static final int KEYTIMEOUT_DAY = 1;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public GoodSourceStaticDO queryUserGoodsSource(long userId) {
        HashOperations hash = redisTemplate.opsForHash();
        Map map = hash.entries(prefix + ":" + userId);
        return UserGoodsSourceCacheConverter.convert2GoodSourceStaticDO(map);
    }

    @Override
    public List<Long> queryUserGoodsChangedHistory() {
        SetOperations set = redisTemplate.opsForSet();
        Set members = set.members(listPrefix);
        return new ArrayList<Long>(members);
    }

    @Override
    public void clearUserGoodsChangedHistory() throws RedisException {
        SetOperations set = redisTemplate.opsForSet();
        set.remove(listPrefix, set.members(listPrefix));
    }

    @Override
    public void increUserGoodsAndHistory(long userId, GoodSourceEnum goodSourceEnum) throws RedisException {
        String hashKey = goodSourceEnum.getName();
        HashOperations hash = redisTemplate.opsForHash();
        SetOperations set = redisTemplate.opsForSet();

        //incr
        hash.increment(prefix + ":" + userId, hashKey, 1);
        redisTemplate.expire(prefix + ":" + userId, KEYTIMEOUT_DAY, TimeUnit.DAYS);

        //make history
        set.add(listPrefix, userId);
    }

    @Override
    public void putUserGoods(GoodSourceStaticDO value) {
        HashOperations hash = redisTemplate.opsForHash();
        String userKey = prefix + ":" + value.getUserDO().getUserId();
        hash.putAll(userKey, UserGoodsSourceCacheConverter.convert2Map(value));
        redisTemplate.expire(userKey, KEYTIMEOUT_DAY, TimeUnit.DAYS);
    }
}
