package com.yasyl.sailtotm.domain.userpreference.ability.instance;

import com.yasyl.sailtotm.domain.userpreference.ability.IQueryUserGoodsSourceAbility;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.domain.userpreference.entity.QueryGoodsSourceModeEnum;
import com.yasyl.sailtotm.domain.userpreference.repository.cache.IStaticUserGoodsSourceCacheRepository;
import com.yasyl.sailtotm.domain.userpreference.repository.dal.IUserGoodsSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-19 23:20
 **/
@Service
public class QueryUserGoodsSourceAbilityInstance implements IQueryUserGoodsSourceAbility {
    @Autowired
    IStaticUserGoodsSourceCacheRepository staticUserGoodsSourceCacheRepository;

    @Autowired
    IUserGoodsSourceRepository userGoodsSourceRepository;

    @Override
    public GoodSourceStaticDO queryGoodsSource(long userId, QueryGoodsSourceModeEnum mode) {
        GoodSourceStaticDO staticDO = staticUserGoodsSourceCacheRepository.queryUserGoodsSource(userId);
        if (staticDO != null || mode == QueryGoodsSourceModeEnum.CACHE_ONLY) {
            return staticDO;
        }
        if (mode == QueryGoodsSourceModeEnum.COMBINE) {
            staticDO = userGoodsSourceRepository.queryUserGoodsSource(userId);
            if (staticDO != null) {
                staticUserGoodsSourceCacheRepository.putUserGoods(staticDO);
                return staticDO;
            }
        }
        return null;
    }

    @Override
    public List<Long/*userId*/> queryChangedUserIdList() {
        List<Long> userIdList = staticUserGoodsSourceCacheRepository.queryUserGoodsChangedHistory();
        if (userIdList == null) {
            userIdList = new ArrayList<>();
        }
        return userIdList;
    }
}
