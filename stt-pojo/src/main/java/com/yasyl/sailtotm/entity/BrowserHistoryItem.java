package com.yasyl.sailtotm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrowserHistoryItem {
    private String createdDate;
    private String picUrl;
    private String numIid;
    private String promotionPrice;
    private String price;
    private String title;
}
