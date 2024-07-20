package com.yasyl.sailtotm.domain.userpreference.ability;

import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceRateDO;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;

/**
 * @program: SailToTm
 * @description: 计算用户商品来源比例能力
 * @author: wujubin
 * @create: 2024-07-19 22:29
 **/
public interface ICalUserGoodsSourceRateAbility {
    GoodSourceRateDO calUserGoodsSourceRate(GoodSourceStaticDO goodSourceStaticRequest);
}
