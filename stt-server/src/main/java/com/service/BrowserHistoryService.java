package com.service;

import com.entity.TaobaoGoodList.Product;
import com.result.PageResult;
import com.vo.ProductDetailListVO;

import java.util.List;

public interface BrowserHistoryService {

    public PageResult getBrowserHistory(String userId, String page, String pageSize);

    public Boolean addBrowserHistory(Product product,String userId);

}
