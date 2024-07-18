package com.yasyl.sailtotm.vo.SearchHIstory;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchHistoryListPageVO {
    private String page;
    private String pageSize;
    private List<SearchItemVO> searchList;
}
