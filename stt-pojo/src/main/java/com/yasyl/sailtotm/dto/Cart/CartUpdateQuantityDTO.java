package com.yasyl.sailtotm.dto.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateQuantityDTO {
    private String cartId;
    private long quantity;
    private String userId;
}