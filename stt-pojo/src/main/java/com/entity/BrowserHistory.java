package com.entity;

import com.entity.TaobaoGoodList.Product;

/**
 * HistoryItems
 */
@lombok.Data
public class BrowserHistory {
    /**
     * 创建历史的日期
     */
    private String createdDate;
    /**
     * 产品信息
     */
    private Product product;
    /**
     * 用户id
     */
    private long userId;
}

