package com.vo.TaobaoGood;


import com.entity.TaobaoGoodList.TaobaoGoodDetail.TaobaoGoodDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaobaoGoodDetailVO {
    private TaobaoGoodDetail item;
}
