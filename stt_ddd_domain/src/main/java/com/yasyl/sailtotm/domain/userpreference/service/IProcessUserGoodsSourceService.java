package com.yasyl.sailtotm.domain.userpreference.service;

import com.yasyl.sailtotm.domain.userpreference.model.request.GoodSourceStaticRequest;
import com.yasyl.sailtotm.domain.userpreference.model.response.GoodSourceRateResponse;

/**
 * @program: SailToTm
 * @description: 用户商品浏览习惯增删改查
 * @author: wujubin
 * @create: 2024-07-19 23:00
 **/
public interface IProcessUserGoodsSourceService {
    /**
     * 同步用户浏览偏好（用于对接定时任务）
     */
    void syncUserGoodsSource();

    /**
     * 初始化用户浏览偏好
     * @param userId
     * @return
     */
    GoodSourceRateResponse initUserGoodsSource(long userId);

    /**
     * 查询用户的浏览偏好
     * @param userId
     * @return
     */
    GoodSourceRateResponse queryUserGoodsSourceRate(long userId);

    /**
     * 统计用户浏览偏好
     * @param staticRequest
     */
    void incrUserGoodsSource(GoodSourceStaticRequest staticRequest);
}
