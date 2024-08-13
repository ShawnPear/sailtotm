package com.yasyl.sailtotm.domain.goods.recommend.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yasyl.sailtotm.domain.goods.recommend.ability.IStaticHotBrowserAbility;
import com.yasyl.sailtotm.domain.goods.recommend.ability.IStaticSearchKeywordsAbility;
import com.yasyl.sailtotm.domain.goods.recommend.service.IRecommendBaseProviderService;
import com.yasyl.sailtotm.domain.goods.supply.ability.ICacheGoodsDetailAbility;
import com.yasyl.sailtotm.domain.goods.supply.ability.ICacheGoodsSimpleAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 00:32
 **/
@Service
public class RecommendBaseProviderService implements IRecommendBaseProviderService {
    private static final int recommendKeySize = 5;
    @Autowired
    IStaticSearchKeywordsAbility staticSearchKeywordsAbility;

    @Autowired
    IStaticHotBrowserAbility staticHotBrowserAbility;

    @Autowired
    ICacheGoodsDetailAbility cacheGoodsDetailAbility;

    @Autowired
    ICacheGoodsSimpleAbility cacheGoodsSimpleAbility;

    @Override
    public List<GoodSimpleDO> recommendByHotBrowser(int page, int size) throws JsonProcessingException {
        List<String> hotBrowserIdList = staticHotBrowserAbility.getHotBrowserNumIid(page, size);
        List<GoodSimpleDO> goodSimpleDOList = new ArrayList<>();
        for (String numIid : hotBrowserIdList) {
            GoodDetailDO detailDO = cacheGoodsDetailAbility.query(numIid);
            if (detailDO == null) {
                continue;
            }
            goodSimpleDOList.add(GoodSimpleDO.buildFromDetail(detailDO));
        }
        return goodSimpleDOList;
    }

    @Override
    public List<GoodSimpleDO> recommendByPersonal(long userId, int page, int size) {
        List<GoodSimpleDO> response = new ArrayList<>();
        List<String> keywords = staticSearchKeywordsAbility.getKeywords(userId, recommendKeySize);
        if (keywords.isEmpty()) {
            return new ArrayList<>();
        }
        int singleQuery = size / recommendKeySize;
        for (String keyword : keywords) {
            List<GoodSimpleDO> simpleDOList = cacheGoodsSimpleAbility.search(keyword, page, singleQuery);
            if (simpleDOList == null || simpleDOList.isEmpty()) {
                continue;
            }
            response.addAll(simpleDOList);
        }
        return response;
    }
}
