package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.yasyl.sailtotm.vo.BrowserHistory.BrowserHistoryListPageVO;

public interface BrowserHistoryService {

    public BrowserHistoryListPageVO getBrowserHistory(String userId, String page, String pageSize);

    public BrowserHistoryListPageVO getBrowserHistoryBySearch(String userId, String page, String pageSize, String q);

    public Boolean addBrowserHistory(Product product, String userId);

}
