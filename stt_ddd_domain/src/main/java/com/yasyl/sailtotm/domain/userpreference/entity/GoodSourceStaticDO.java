package com.yasyl.sailtotm.domain.userpreference.entity;

import com.yasyl.sailtotm.common.enums.GoodSourceEnum;
import com.yasyl.sailtotm.domain.payment.entity.UserDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SailToTm
 * @description: 商品来源数量统计
 * @author: wujubin
 * @create: 2024-07-19 22:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodSourceStaticDO {
    /**
     * 用户信息
     */
    private UserDO userDO;

    /**
     * 淘宝商品
     */
    private int taobaoClickStatic;

    /**
     * 拼多多商品比例
     */
    private int pddClickStatic;

    /**
     * 京东商品比例
     */
    private int jdClickStatic;

    public static GoodSourceStaticDO init(long userId) {
        return GoodSourceStaticDO.builder()
                .jdClickStatic(1)
                .pddClickStatic(1)
                .taobaoClickStatic(1)
                .userDO(UserDO.builder()
                        .userId(userId)
                        .build())
                .build();
    }

    public void incr(GoodSourceEnum sourceEnum) {
        switch (sourceEnum) {
            case TB:
                taobaoClickStatic += 1;
                break;
            case PDD:
                pddClickStatic += 1;
                break;
            case JD:
                jdClickStatic += 1;
                break;
        }
    }
}
