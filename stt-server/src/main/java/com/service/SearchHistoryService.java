package com.service;

import com.vo.SearchHIstory.SearchHistoryListPageVO;

public interface SearchHistoryService {
    public SearchHistoryListPageVO getSearchHistory(String userId, String page, String pageSize);

    public Boolean addSearchHistory(String q, Long userId);
}
