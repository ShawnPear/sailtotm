package com.yasyl.sailtotm.infra.redis.converter;

import com.yasyl.sailtotm.domain.payment.entity.UserDO;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.infra.redis.model.UserGoodsSourcePreferenceValue;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-20 10:37
 **/
public class UserGoodsSourceCacheConverter {
    public static UserGoodsSourcePreferenceValue convert2UserGoodsSourcePreferenceValue(GoodSourceStaticDO staticDO) {
        UserGoodsSourcePreferenceValue po = new UserGoodsSourcePreferenceValue();
        po.setUserId(Optional.ofNullable(staticDO).map(GoodSourceStaticDO::getUserDO).map(UserDO::getUserId).orElse(0L));
        po.setTb(staticDO.getTaobaoClickStatic());
        po.setJd(staticDO.getJdClickStatic());
        po.setPdd(staticDO.getJdClickStatic());
        po.setValid(1);
        po.setCommonExt("");
        return po;
    }

    public static GoodSourceStaticDO convert2GoodSourceStaticDO(UserGoodsSourcePreferenceValue staticPO) {
        GoodSourceStaticDO sourcePreferenceDO = new GoodSourceStaticDO();
        sourcePreferenceDO.setUserDO(UserDO.builder().userId(staticPO.getUserId()).build());
        sourcePreferenceDO.setTaobaoClickStatic(staticPO.getTb());
        sourcePreferenceDO.setJdClickStatic(staticPO.getJd());
        sourcePreferenceDO.setPddClickStatic(staticPO.getPdd());
        return sourcePreferenceDO;
    }

    public static GoodSourceStaticDO convert2GoodSourceStaticDO(Map<String, String> map) {
        GoodSourceStaticDO sourcePreferenceDO = new GoodSourceStaticDO();
        sourcePreferenceDO.setUserDO(UserDO.builder().userId(Long.parseLong(map.get("userId"))).build());
        sourcePreferenceDO.setTaobaoClickStatic(Integer.parseInt(map.get("tb")));
        sourcePreferenceDO.setJdClickStatic(Integer.parseInt(map.get("jd")));
        sourcePreferenceDO.setPddClickStatic(Integer.parseInt(map.get("pdd")));
        return sourcePreferenceDO;
    }

    public static Map<String, String> convert2Map(UserGoodsSourcePreferenceValue value) {
        HashMap<String, String> map = new HashMap<>();

        map.put("userId", String.valueOf(value.getUserId()));
        map.put("tb", String.valueOf(value.getTb()));
        map.put("pdd", String.valueOf(value.getPdd()));
        map.put("jd", String.valueOf(value.getJd()));
        map.put("ctime", String.valueOf(value.getCtime()));
        map.put("utime", String.valueOf(value.getUtime()));
        map.put("valid", String.valueOf(value.getValid()));
        map.put("commonExt", String.valueOf(value.getCommonExt()));

        return map;
    }

    public static Map<String, String> convert2Map(GoodSourceStaticDO value) {
        HashMap<String, String> map = new HashMap<>();

        map.put("userId", String.valueOf(value.getUserDO().getUserId()));
        map.put("tb", String.valueOf(value.getTaobaoClickStatic()));
        map.put("pdd", String.valueOf(value.getPddClickStatic()));
        map.put("jd", String.valueOf(value.getJdClickStatic()));
        map.put("ctime", String.valueOf(Instant.now().getEpochSecond()));
        map.put("utime", String.valueOf(Instant.now().getEpochSecond()));
        map.put("valid", String.valueOf(1));
        map.put("commonExt", String.valueOf(""));

        return map;
    }
}
