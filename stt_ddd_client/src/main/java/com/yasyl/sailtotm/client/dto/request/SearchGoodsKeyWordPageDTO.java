package com.yasyl.sailtotm.client.dto.request;

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