// Items.java

package com.yasyl.sailtotm.entity.TaobaoGoodList;

import java.util.List;

@lombok.Data
public class Items {
    private String dataFrom;
    private List<Product> item;
    private long itemWeightUpdate;
    private String page;
    private long pageSize;
    private String pagecount;
    private String realTotalResults;
    private String totalResults;
}

// Item.java