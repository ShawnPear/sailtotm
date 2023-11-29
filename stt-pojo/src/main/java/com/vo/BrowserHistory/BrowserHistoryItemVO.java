package com.vo.BrowserHistory;

import com.vo.ProductSimpleDetailVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrowserHistoryItemVO {
    private String createdDate;
    private ProductSimpleDetailVO productDetail;
}

