package com.yasyl.sailtotm.domain.userpreference.service.impl;

import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.common.exception.user.ParamMissingException;
import com.yasyl.sailtotm.common.exception.user.UserGoodSourceException;
import com.yasyl.sailtotm.domain.payment.entity.UserDO;
import com.yasyl.sailtotm.domain.userpreference.ability.ICalUserGoodsSourceRateAbility;
import com.yasyl.sailtotm.domain.userpreference.ability.IQueryUserGoodsSourceAbility;
import com.yasyl.sailtotm.domain.userpreference.ability.ISyncUserGoodsSourceAbility;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceRateDO;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.domain.userpreference.entity.QueryGoodsSourceModeEnum;
import com.yasyl.sailtotm.domain.userpreference.model.request.GoodSourceStaticRequest;
import com.yasyl.sailtotm.domain.userpreference.model.response.GoodSourceRateResponse;
import com.yasyl.sailtotm.domain.userpreference.service.IProcessUserGoodsSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: SailToTm
 * @description: 用户商品浏览习惯增删改查
 * @author: wujubin
 * @create: 2024-07-19 23:04
 **/
@Service
@Slf4j
public class ProcessUserGoodsSourceService implements IProcessUserGoodsSourceService {

    @Autowired
    private ICalUserGoodsSourceRateAbility calUserGoodsSourceRateAbility;

    @Autowired
    private IQueryUserGoodsSourceAbility queryUserGoodsSourceAbility;

    @Autowired
    private ISyncUserGoodsSourceAbility syncUserGoodsSourceAbility;

    @Qualifier("userGoodsPreferenceSyncThreadPool")
    @Autowired
    private ThreadPoolTaskExecutor userGoodsPreferenceSyncThreadPool;

    @Override
    public void syncUserGoodsSource() throws RedisException {
        List<Long> changedUserIdList = queryUserGoodsSourceAbility.queryChangedUserIdList();
        if (changedUserIdList == null || changedUserIdList.isEmpty()) {
            return;
        }
        for (Long userId : changedUserIdList) {
            userGoodsPreferenceSyncThreadPool.execute(() -> {
                GoodSourceStaticDO sourceStaticDO = queryUserGoodsSourceAbility.queryGoodsSource(userId, QueryGoodsSourceModeEnum.CACHE_ONLY);
                if (sourceStaticDO == null) {
                    return;
                }
                if (!syncUserGoodsSourceAbility.syncUserGoodsSource(sourceStaticDO)) {
                    log.error("redis同步错误,userId:{}", sourceStaticDO.getUserDO().getUserId());
                }
            });
        }
        syncUserGoodsSourceAbility.clearChangedGoodsSourceCache();
    }

    @Override
    public GoodSourceRateResponse queryUserGoodsSourceRate(long userId) throws UserGoodSourceException {
        GoodSourceStaticDO sourceStaticDO = queryUserGoodsSourceAbility.queryGoodsSource(userId, QueryGoodsSourceModeEnum.COMBINE);
        if (sourceStaticDO == null) {
            throw new UserGoodSourceException("用户商品浏览偏好未初始化");
        }
        GoodSourceRateDO rateDO = calUserGoodsSourceRateAbility.calUserGoodsSourceRate(sourceStaticDO);
        return GoodSourceRateResponse.builder()
                .goodSourceStaticDO(rateDO.getGoodSourceStaticDO())
                .userId(rateDO.getUserId())
                .rateSum(rateDO.getRateSum())
                .jdRate(rateDO.getJdRate())
                .pddRate(rateDO.getPddRate())
                .taobaoRate(rateDO.getTaobaoRate())
                .build();
    }

    @Override
    public void incrUserGoodsSource(GoodSourceStaticRequest staticRequest) throws RedisException, UserGoodSourceException {
        long userId = Optional.ofNullable(staticRequest).map(GoodSourceStaticRequest::getUserDO).map(UserDO::getUserId).orElse(0L);
        if (userId == 0L) {
            throw new ParamMissingException("未传入UserId");
        }

        GoodSourceStaticDO queryResult = queryUserGoodsSourceAbility.queryGoodsSource(userId, QueryGoodsSourceModeEnum.CACHE_ONLY);
        if (queryResult == null) {
            throw new UserGoodSourceException("用户未初始化");
        }

        syncUserGoodsSourceAbility.increUserGoodsAndHistory(userId, staticRequest.getSourceEnum());
    }

    @Override
    public GoodSourceRateResponse initUserGoodsSource(long userId) {
        GoodSourceStaticDO staticDO = queryUserGoodsSourceAbility.queryGoodsSource(userId, QueryGoodsSourceModeEnum.COMBINE);
        if (staticDO != null) {
            throw new UserGoodSourceException("用户商品浏览偏好数据已存在，无法重复初始化");
        }
        GoodSourceStaticDO initStaticDO = GoodSourceStaticDO.init(userId);

        syncUserGoodsSourceAbility.syncUserGoodsSource(initStaticDO);
        GoodSourceRateDO rateDO = calUserGoodsSourceRateAbility.calUserGoodsSourceRate(initStaticDO);
        return GoodSourceRateResponse.builder()
                .goodSourceStaticDO(rateDO.getGoodSourceStaticDO())
                .userId(rateDO.getUserId())
                .rateSum(rateDO.getRateSum())
                .jdRate(rateDO.getJdRate())
                .pddRate(rateDO.getPddRate())
                .taobaoRate(rateDO.getTaobaoRate())
                .build();
    }
}
