package com.dto.Search;

@lombok.Data
public class SearchGoodsKeyWordPageDTO {
    /**
     * 搜索页面
     */
    private String page;
    /**
     * 搜索关键词
     */
    private String q;
}