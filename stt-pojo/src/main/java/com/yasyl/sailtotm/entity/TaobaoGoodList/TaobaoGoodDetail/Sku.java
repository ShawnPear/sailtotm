// Sku.java

package com.yasyl.sailtotm.entity.TaobaoGoodList.TaobaoGoodDetail;

import com.yasyl.sailtotm.entity.PropertiesName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sku {
    private String orginalPrice;
    private String price;
    private String properties;
    private String propertiesName;
    private String quantity;
    private List<String> propertiesList;
    private List<PropertiesName> propertiesNameList;
}