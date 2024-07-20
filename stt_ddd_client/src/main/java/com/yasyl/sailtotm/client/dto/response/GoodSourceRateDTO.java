package com.yasyl.sailtotm.client.dto.response;

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
public class GoodSourceRateDTO {
    /**
     * userID
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


}
