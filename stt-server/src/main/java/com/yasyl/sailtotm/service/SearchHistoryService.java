package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.vo.SearchHIstory.SearchHistoryListPageVO;

public interface SearchHistoryService {
    public SearchHistoryListPageVO getSearchHistory(String userId, String page, String pageSize);

    public Boolean addSearchHistory(String q, Long userId);
}
