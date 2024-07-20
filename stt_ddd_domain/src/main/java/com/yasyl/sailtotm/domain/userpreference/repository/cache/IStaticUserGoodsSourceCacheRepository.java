package com.yasyl.sailtotm.domain.userpreference.repository.cache;

import com.yasyl.sailtotm.common.enums.GoodSourceEnum;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;

import java.util.List;

/**
 * @program: SailToTm
 * @description: redis，累加用户观看记录
 * @author: wujubin
 * @create: 2024-07-19 22:58
 **/
public interface IStaticUserGoodsSourceCacheRepository {
    GoodSourceStaticDO queryUserGoodsSource(long userId);

    List<Long> queryUserGoodsChangedHistory();

    void clearUserGoodsChangedHistory() throws RedisException;

    void increUserGoodsAndHistory(long userId, GoodSourceEnum goodSourceEnum) throws RedisException;
    
    void putUserGoods(GoodSourceStaticDO value);
}
