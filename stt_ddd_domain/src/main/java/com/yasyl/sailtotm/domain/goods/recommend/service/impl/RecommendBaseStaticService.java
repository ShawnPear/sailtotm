package com.yasyl.sailtotm.domain.goods.recommend.service.impl;

import com.yasyl.sailtotm.domain.goods.recommend.ability.IStaticSearchKeywordsAbility;
import com.yasyl.sailtotm.domain.goods.recommend.service.IRecommendBaseStaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 00:33
 **/
@Service
public class RecommendBaseStaticService implements IRecommendBaseStaticService {
    @Autowired
    IStaticSearchKeywordsAbility staticSearchKeywordsAbility;
    
    @Override
    public void staticKeywords(long userId, String keyWords) {
        staticSearchKeywordsAbility.staticKeywords(userId,keyWords);
    }
}
