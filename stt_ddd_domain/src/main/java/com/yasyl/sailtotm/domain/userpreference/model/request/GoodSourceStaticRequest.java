package com.yasyl.sailtotm.domain.userpreference.model.request;

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
public class GoodSourceStaticRequest {
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

    /**
     * 自增使用
     * 商品来源
     */
    private GoodSourceEnum sourceEnum;
}
