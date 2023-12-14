// Prop.java

package com.entity.TaobaoGoodList.TaobaoGoodDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prop {
    private String name;
    private String value;
}