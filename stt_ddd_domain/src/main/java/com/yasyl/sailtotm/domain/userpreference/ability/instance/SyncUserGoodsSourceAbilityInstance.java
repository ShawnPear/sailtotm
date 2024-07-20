package com.yasyl.sailtotm.domain.userpreference.ability.instance;

import com.yasyl.sailtotm.common.enums.GoodSourceEnum;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.common.exception.user.UserGoodSourceException;
import com.yasyl.sailtotm.domain.userpreference.ability.ISyncUserGoodsSourceAbility;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.domain.userpreference.repository.cache.IStaticUserGoodsSourceCacheRepository;
import com.yasyl.sailtotm.domain.userpreference.repository.dal.IUserGoodsSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-19 23:14
 **/
@Service
public class SyncUserGoodsSourceAbilityInstance implements ISyncUserGoodsSourceAbility {
    @Autowired
    IStaticUserGoodsSourceCacheRepository staticUserGoodsSourceCacheRepository;

    @Autowired
    IUserGoodsSourceRepository userGoodsSourceRepository;


    @Override
    public boolean syncUserGoodsSource(GoodSourceStaticDO goodSourceStaticDO) throws RedisException, UserGoodSourceException {
        GoodSourceStaticDO sourceStaticDO = userGoodsSourceRepository.queryUserGoodsSource(goodSourceStaticDO.getUserDO().getUserId());
        if (sourceStaticDO == null) {
            return userGoodsSourceRepository.insertUserGoodsSource(goodSourceStaticDO);
        } else {
            return userGoodsSourceRepository.updateUserGoodsSource(goodSourceStaticDO);
        }
    }

    @Override
    public void clearChangedGoodsSourceCache() throws RedisException {
        staticUserGoodsSourceCacheRepository.clearUserGoodsChangedHistory();
    }

    @Override
    public void increUserGoodsAndHistory(long userId, GoodSourceEnum goodSourceEnum) throws RedisException {
        staticUserGoodsSourceCacheRepository.increUserGoodsAndHistory(userId,goodSourceEnum);
    }
}
