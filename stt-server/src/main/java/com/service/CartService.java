package com.service;

import com.dto.Cart.CartDelDTO;
import com.dto.Cart.CartUpdateQuantityDTO;
import com.entity.TaobaoGoodList.Product;
import com.vo.Cart.CartItemVO;
import com.vo.Cart.CartListPageVO;

public interface CartService {
    public CartListPageVO getCart(String userId, String page, String pageSize);

    public CartListPageVO getCartBySearch(String userId, String page, String pageSize, String q);

    Boolean addCart(Product product, String userId, Integer quantity, String properties, String propertiesName);

    public Boolean delCart(CartDelDTO dto);

    public Boolean updateCart(CartUpdateQuantityDTO dto);

    CartItemVO getCartByProduct(String numIid, String userId, String propertiesName);
}
