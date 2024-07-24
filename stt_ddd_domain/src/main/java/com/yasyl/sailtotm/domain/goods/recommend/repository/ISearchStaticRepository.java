package com.yasyl.sailtotm.domain.goods.recommend.repository;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 08:17
 **/
public interface ISearchStaticRepository {
    void save(long userId,String keyword);
    
    List<String> queryTopSearchKeywords(long userId,int size);
}
