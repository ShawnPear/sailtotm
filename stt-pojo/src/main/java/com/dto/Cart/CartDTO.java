package com.dto.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
}