package com.yasyl.sailtotm.infra.dal.dao;

import com.yasyl.sailtotm.infra.dal.entity.SearchKeywordsHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 10:03
 **/
@Mapper
public interface SearchKeywordsHistoryDao {
    Boolean update(long userId, String keyword);
    
    Boolean insert(long userId, String keyword);
    
    List<SearchKeywordsHistory> queryByUserId(long userId, int size);
    
    SearchKeywordsHistory queryByUserIdAndKeyword(long userId,String keyword);
}
