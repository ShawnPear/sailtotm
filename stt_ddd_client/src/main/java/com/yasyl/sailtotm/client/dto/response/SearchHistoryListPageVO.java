package com.yasyl.sailtotm.client.dto.response;

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

@Data
@Builder
class SearchItemVO {
    private String updatedDate;
    private String q;
}