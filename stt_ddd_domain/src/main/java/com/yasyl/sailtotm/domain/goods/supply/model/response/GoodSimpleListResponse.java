// Response.java

package com.yasyl.sailtotm.domain.goods.supply.model.response;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodSimpleListResponse {
    private String dataFrom;
    private List<GoodSimpleDO> item;
    private long itemWeightUpdate;
    private String page;
    private long pageSize;
    private String pagecount;
    private String realTotalResults;
    private String totalResults;
}
