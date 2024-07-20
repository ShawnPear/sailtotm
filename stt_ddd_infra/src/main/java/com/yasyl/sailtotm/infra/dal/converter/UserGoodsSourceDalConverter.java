package com.yasyl.sailtotm.infra.dal.converter;

import com.yasyl.sailtotm.domain.payment.entity.UserDO;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.infra.dal.entity.UserGoodsSourcePreferencePO;

import java.util.Optional;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-20 10:37
 **/
public class UserGoodsSourceDalConverter {
    public static UserGoodsSourcePreferencePO convert2UserGoodsSourcePreferencePO(GoodSourceStaticDO staticDO) {
        UserGoodsSourcePreferencePO po = new UserGoodsSourcePreferencePO();
        po.setUserId(Optional.ofNullable(staticDO).map(GoodSourceStaticDO::getUserDO).map(UserDO::getUserId).orElse(0L));
        po.setTb(staticDO.getTaobaoClickStatic());
        po.setJd(staticDO.getJdClickStatic());
        po.setPdd(staticDO.getJdClickStatic());
        po.setValid(1);
        po.setCommonExt("");
        return po;
    }

    public static GoodSourceStaticDO convert2UserGoodsSourcePreferenceDO(UserGoodsSourcePreferencePO staticPO) {
        GoodSourceStaticDO sourcePreferenceDO = new GoodSourceStaticDO();
        sourcePreferenceDO.setUserDO(UserDO.builder().userId(staticPO.getUserId()).build());
        sourcePreferenceDO.setTaobaoClickStatic(staticPO.getTb());
        sourcePreferenceDO.setJdClickStatic(staticPO.getJd());
        sourcePreferenceDO.setPddClickStatic(staticPO.getPdd());
        return sourcePreferenceDO;
    }
}
