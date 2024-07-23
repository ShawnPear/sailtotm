package com.yasyl.sailtotm.domain.goods.supply.model.response;


import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodDetailResponse {
    private GoodDetailDO item;
}