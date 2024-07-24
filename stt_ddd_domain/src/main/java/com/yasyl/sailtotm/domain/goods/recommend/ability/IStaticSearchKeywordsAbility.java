package com.yasyl.sailtotm.domain.goods.recommend.ability;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 21:50
 **/
public interface IStaticSearchKeywordsAbility {
    void staticKeywords(long userId, String keyWords);

    List<String> getKeywords(long userId, int size);
}
