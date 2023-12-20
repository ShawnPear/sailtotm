// Items.java

package com.entity.TaobaoGoodList;

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