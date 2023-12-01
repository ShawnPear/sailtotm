package com.service;

import com.dto.Cart.CartDelDTO;
import com.dto.Cart.CartUpdateQuantityDTO;
import com.entity.TaobaoGoodList.Product;
import com.vo.Cart.CartListPageVO;

public interface CartService {
    public CartListPageVO getCart(String userId, String page, String pageSize);

    public CartListPageVO getCartBySearch(String userId, String page, String pageSize, String q);

    public Boolean addCart(Product product, String userId, Integer quantity);

    public Boolean delCart(CartDelDTO dto);

    public Boolean updateCart(CartUpdateQuantityDTO dto);
}
