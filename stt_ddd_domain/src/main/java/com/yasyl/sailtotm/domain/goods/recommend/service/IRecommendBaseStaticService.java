package com.yasyl.sailtotm.domain.goods.recommend.service;

/**
 * @program: SailToTm
 * @description: 个性化推荐页兜底方案统计
 * @author: wujubin
 * @create: 2024-07-23 00:32
 **/
public interface IRecommendBaseStaticService {
    void staticKeywords(long userId, String keyWords);
}
