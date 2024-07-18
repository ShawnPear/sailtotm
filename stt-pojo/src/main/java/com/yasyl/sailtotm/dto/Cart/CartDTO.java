package com.yasyl.sailtotm.dto.Cart;

import com.yasyl.sailtotm.entity.PropertiesName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private String userId;
    private String numIid;
    private String picUrl;
    private String title;
    private String promotionPrice;
    private String price;
    private Integer quantity;
    private String properties;
    private String propertiesName;
    private List<String> propertiesList;
    private List<PropertiesName> propertiesNameList;
}