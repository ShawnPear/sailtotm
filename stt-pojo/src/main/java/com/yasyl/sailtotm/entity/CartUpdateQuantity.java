package com.yasyl.sailtotm.entity;

import lombok.Builder;

@lombok.Data
@Builder
public class CartUpdateQuantity {
    private String cartId;
    private long quantity;
    private String userId;
}