package com.yasyl.sailtotm.domain.goods.recommend.ability.instance;

import com.yasyl.sailtotm.domain.goods.recommend.ability.IStaticSearchKeywordsAbility;
import com.yasyl.sailtotm.domain.goods.recommend.repository.ISearchStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 08:20
 **/
@Service
public class StaticSearchKeywordsAbilityInstance implements IStaticSearchKeywordsAbility {
    @Autowired
    ISearchStaticRepository searchStaticRepository;

    @Override
    public void staticKeywords(long userId, String keyWords) {
        searchStaticRepository.save(userId, keyWords);
    }

    @Override
    public List<String> getKeywords(long userId, int size) {
        return searchStaticRepository.queryTopSearchKeywords(userId, size);
    }
}
