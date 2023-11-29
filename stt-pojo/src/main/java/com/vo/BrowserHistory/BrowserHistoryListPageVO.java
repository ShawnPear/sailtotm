package com.vo.BrowserHistory;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrowserHistoryListPageVO {
    String page;
    String pageSize;
    List<BrowserHistoryItemVO> product_detail_list;
}
