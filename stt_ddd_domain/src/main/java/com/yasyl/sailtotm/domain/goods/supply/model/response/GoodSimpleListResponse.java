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
    private List<GoodSimpleDO> item;
}
