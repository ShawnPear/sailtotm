package com.yasyl.sailtotm.domain.userpreference.ability;

import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.domain.userpreference.entity.QueryGoodsSourceModeEnum;

import java.util.List;

/**
 * @program: SailToTm
 * @description: 查询用户商品偏好（redis、mysql）
 * @author: wujubin
 * @create: 2024-07-19 23:16
 **/
public interface IQueryUserGoodsSourceAbility {

    GoodSourceStaticDO queryGoodsSource(long userId, QueryGoodsSourceModeEnum mode);
    
    List<Long> queryChangedUserIdList();
}
