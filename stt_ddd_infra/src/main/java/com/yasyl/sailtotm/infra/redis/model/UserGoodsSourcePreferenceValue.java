package com.yasyl.sailtotm.infra.redis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SailToTm
 * @description: 用户浏览偏好Hash
 * @author: wujubin
 * @create: 2024-07-20 10:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGoodsSourcePreferenceValue {
    private long userId;
    private int tb;
    private int pdd;
    private int jd;

    private Integer valid;
    private Integer ctime;
    private Integer utime;
    private String commonExt;
}
