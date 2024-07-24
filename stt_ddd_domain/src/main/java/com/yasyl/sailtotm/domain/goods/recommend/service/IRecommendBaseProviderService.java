package com.yasyl.sailtotm.domain.goods.recommend.service;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;

import java.util.List;

/**
 * @program: SailToTm
 * @description: 个性化推荐页兜底方案
 * @author: wujubin
 * @create: 2024-07-23 00:31
 **/
public interface IRecommendBaseProviderService {

    List<GoodSimpleDO> recommendByHotBrowser(int page, int size);

    List<GoodSimpleDO> recommendByPersonal(long userId, int page, int size);
}
