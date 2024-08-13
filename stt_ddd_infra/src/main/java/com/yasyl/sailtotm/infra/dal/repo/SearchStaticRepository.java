package com.yasyl.sailtotm.infra.dal.repo;

import com.yasyl.sailtotm.domain.goods.recommend.repository.ISearchStaticRepository;
import com.yasyl.sailtotm.infra.dal.dao.SearchKeywordsHistoryDao;
import com.yasyl.sailtotm.infra.dal.entity.SearchKeywordsHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 08:19
 **/
@Service
public class SearchStaticRepository implements ISearchStaticRepository {
    @Autowired
    SearchKeywordsHistoryDao mapper;

    @Override
    public void save(long userId, String keyword) {
        if (userId == 0L || keyword == null || keyword.isEmpty()) {
            return;
        }
        if (mapper.queryByUserIdAndKeyword(userId, keyword) != null) {
            mapper.update(userId, keyword);
        } else {
            mapper.insert(userId, keyword);
        }
    }

    @Override
    public List<String> queryTopSearchKeywords(long userId, int size) {
        List<SearchKeywordsHistory> searchKeywordsHistories = mapper.queryByUserId(userId, size);
        List<String> keywordsList = new ArrayList<>();
        if (searchKeywordsHistories == null) {
            return keywordsList;
        }
        for (SearchKeywordsHistory searchKeywordsHistory : searchKeywordsHistories) {
            keywordsList.add(searchKeywordsHistory.getKeyword());
        }
        return keywordsList;
    }
}
