package com.dto;

@lombok.Data
public class SearchKeyWordPageDTO {
    /**
     * 搜索页面
     */
    private String page;
    /**
     * 搜索关键词
     */
    private String q;
}