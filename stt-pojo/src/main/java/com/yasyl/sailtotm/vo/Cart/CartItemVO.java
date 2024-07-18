package com.yasyl.sailtotm.vo.Cart;

import com.yasyl.sailtotm.entity.PropertiesName;
import com.yasyl.sailtotm.vo.ProductSimpleDetailVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVO {
    private Integer cartId;
    private String createdDate;
    private Integer quantity;
    private String properties;
    private String propertiesName;
    private List<String> propertiesList;
    private List<PropertiesName> propertiesNameList;
    private ProductSimpleDetailVO productDetail;
}

