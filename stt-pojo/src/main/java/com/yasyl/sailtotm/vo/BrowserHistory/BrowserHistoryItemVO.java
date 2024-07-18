package com.yasyl.sailtotm.vo.BrowserHistory;

import com.yasyl.sailtotm.vo.ProductSimpleDetailVO;
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

