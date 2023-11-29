package com.dto.Cart;

@lombok.Data
public class CartDTO {
    private String userId;
    private String numIid;
    private String picUrl;
    private String title;
    private String promotionPrice;
    private String price;
    private Integer quantity;
    private String properties;
    private String properties_name;
}