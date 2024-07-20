package com.yasyl.sailtotm.domain.userpreference.model.response;

import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SailToTm
 * @description: 商品来源比例（加和为10）
 * @author: wujubin
 * @create: 2024-07-19 22:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodSourceRateResponse {
    /**
     * 商品来源统计（元数据）
     */
    private GoodSourceStaticDO goodSourceStaticDO;
    
    /**
     * 
     */
    private long userId;
    
    /**
     * 商品比例总和
     */
    private int rateSum;

    /**
     * 淘宝商品比例
     */
    private int taobaoRate;

    /**
     * 拼多多商品比例
     */
    private int pddRate;

    /**
     * 京东商品比例
     */
    private int jdRate;

    /**
     * 初始userId工厂
     * @param userId
     * @return
     */
    public static GoodSourceRateResponse init(long userId){
        GoodSourceRateResponse rateResponse = new GoodSourceRateResponse();
        rateResponse.userId = userId;
        rateResponse.jdRate = 1;
        rateResponse.pddRate = 1;
        rateResponse.taobaoRate = 1;
        rateResponse.rateSum = 3;
        return rateResponse;
    }
}
