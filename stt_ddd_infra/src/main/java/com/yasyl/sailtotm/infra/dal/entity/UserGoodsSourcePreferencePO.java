package com.yasyl.sailtotm.infra.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SailToTm
 * @description: 用户浏览偏好实体类
 * @author: wujubin
 * @create: 2024-07-20 10:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGoodsSourcePreferencePO {
    private long userId;
    private int tb;
    private int pdd;
    private int jd;
    
    private Integer valid;
    private Integer ctime;
    private Integer utime;
    private String commonExt;
}
