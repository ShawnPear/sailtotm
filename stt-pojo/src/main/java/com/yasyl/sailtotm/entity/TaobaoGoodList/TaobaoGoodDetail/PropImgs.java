// PropImgs.java

package com.yasyl.sailtotm.entity.TaobaoGoodList.TaobaoGoodDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropImgs {
    private List<PropImg> propImg;
}