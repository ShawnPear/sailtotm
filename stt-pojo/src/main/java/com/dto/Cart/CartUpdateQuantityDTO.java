package com.dto.Cart;

@lombok.Data
public class CartUpdateQuantityDTO {
    private String cartId;
    private long quantity;
    private String userId;
}