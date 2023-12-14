// Skus.java

package com.entity.TaobaoGoodList.TaobaoGoodDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skus {
    private List<Sku> sku;
}