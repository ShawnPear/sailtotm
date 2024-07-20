package com.yasyl.sailtotm.client.dto.request;

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
public class GoodSourceIncrDTO {
    /**
     * userID
     */
    private long userId;

    /**
     * 商品来源
     */
    private int source;
}
