package com.yasyl.sailtotm.domain.userpreference.repository.dal;

import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;

/**
 * @program: SailToTm
 * @description: mysql更新用户信息
 * @author: wujubin
 * @create: 2024-07-19 22:56
 **/
public interface IUserGoodsSourceRepository {
    GoodSourceStaticDO queryUserGoodsSource(long userId);

    boolean updateUserGoodsSource(GoodSourceStaticDO goodSourceStaticDO);

    boolean insertUserGoodsSource(GoodSourceStaticDO goodSourceStaticDO);
}
