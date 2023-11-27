package com.service.impl;

import com.entity.BrowserHistory;
import com.entity.TaobaoGoodList.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.BrowserHistoryMapper;
import com.result.PageResult;
import com.service.BrowserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BrowserHistoryServiceImpl implements BrowserHistoryService {
    @Autowired
    BrowserHistoryMapper browserHistoryMapper;

    @Override
    public PageResult getBrowserHistory(String userId, String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<BrowserHistory> browserHistories = browserHistoryMapper.selectAllByUserIdPage(userId);
        return new PageResult(browserHistories.getTotal(), browserHistories.getResult());
    }

    @Override
    public Boolean addBrowserHistory(Product product, String userId) {
        LocalDateTime createdTime = LocalDateTime.now();
        return browserHistoryMapper.insert(product, createdTime, userId) > 0;
    }
}
