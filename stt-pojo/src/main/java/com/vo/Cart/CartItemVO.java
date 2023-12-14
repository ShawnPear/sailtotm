package com.vo.Cart;

import com.vo.ProductSimpleDetailVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
    private ProductSimpleDetailVO productDetail;
}

