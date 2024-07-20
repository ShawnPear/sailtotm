package com.yasyl.sailtotm.domain.userpreference.ability;

import com.yasyl.sailtotm.common.enums.GoodSourceEnum;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;

/**
 * @program: SailToTm
 * @description: 定时任务，redis->mysql，同步消息
 * @author: wujubin
 * @create: 2024-07-19 22:54
 **/
public interface ISyncUserGoodsSourceAbility {
    boolean syncUserGoodsSource(GoodSourceStaticDO goodSourceStaticDO);
    
    void clearChangedGoodsSourceCache() throws RedisException;

    void increUserGoodsAndHistory(long userId, GoodSourceEnum goodSourceEnum) throws RedisException;
}
