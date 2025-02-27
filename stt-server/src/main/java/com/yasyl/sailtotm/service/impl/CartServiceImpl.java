package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.dto.Cart.CartDelDTO;
import com.yasyl.sailtotm.dto.Cart.CartUpdateQuantityDTO;
import com.yasyl.sailtotm.entity.CartItem;
import com.yasyl.sailtotm.entity.CartUpdateQuantity;
import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.yasyl.sailtotm.entity.TranslatorDict;
import com.yasyl.sailtotm.enumeration.TranslatorType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yasyl.sailtotm.mapper.CartMapper;
import com.yasyl.sailtotm.mapper.mapper_helper.OneBoundApiTaobaoProductMapperHelper;
import com.yasyl.sailtotm.service.CartService;
import com.yasyl.sailtotm.service.SkuPropService;
import com.yasyl.sailtotm.service.TranslatorService;
import com.yasyl.sailtotm.vo.Cart.CartItemVO;
import com.yasyl.sailtotm.vo.Cart.CartListPageVO;
import com.yasyl.sailtotm.vo.ProductSimpleDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    OneBoundApiTaobaoProductMapperHelper oneBoundApiTaobaoProductMapperHelper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    SkuPropService skuPropService;

    @Autowired
    TranslatorService translator;

    @Override
    public CartListPageVO getCart(String userId, String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<CartItem> CartItems = cartMapper.selectAllByUserIdPage(userId);
        List<CartItemVO> result = new ArrayList<>();
        HashMap<String, TranslatorDict> tranDict = new HashMap<>();
        for (CartItem item : CartItems.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            CartItemVO cartItemVO = CartItemVO.builder()
                    .cartId(item.getCartId())
                    .createdDate(item.getCreatedDate())
                    .productDetail(psdVO)
                    .propertiesName(item.getPropertiesName())
                    .properties(item.getProperties())
                    .quantity(item.getQuantity())
                    .build();
//            skuPropService.extractSkuToTranDict(cartItemVO, tranDict);
            result.add(cartItemVO);
        }
//        translator.translatorCache(tranDict, TranslatorType.ZH2RU);
        return CartListPageVO.builder()
                .page(page)
                .pageSize(String.valueOf(CartItems.getPageSize()))
                .product_detail_list(result)
                .build();
    }

    @Override
    public CartListPageVO getCartBySearch(String userId, String page, String pageSize, String q) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<CartItem> CartItems = cartMapper.selectAllByUserSearchPage(userId, '%' + q + '%');
        List<CartItemVO> result = new ArrayList<>();
        HashMap<String, TranslatorDict> tranDict = new HashMap<>();
        for (CartItem item : CartItems.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            CartItemVO cartItemVO = CartItemVO.builder()
                    .cartId(item.getCartId())
                    .createdDate(item.getCreatedDate())
                    .productDetail(psdVO)
                    .propertiesName(item.getPropertiesName())
                    .properties(item.getProperties())
                    .quantity(item.getQuantity())
                    .build();
            skuPropService.extractSkuToTranDict(cartItemVO, tranDict);
            result.add(cartItemVO);
        }
        translator.translatorCache(tranDict, TranslatorType.ZH2RU);
        return CartListPageVO.builder()
                .page(page)
                .pageSize(String.valueOf(CartItems.getPageSize()))
                .product_detail_list(result)
                .build();
    }

    @Override
    public Boolean addCart(Product product, String userId, Integer quantity, String properties, String propertiesName) {
        if (quantity == null) {
            quantity = 1;
        }
        LocalDateTime createdTime = LocalDateTime.now();
        oneBoundApiTaobaoProductMapperHelper.insertOrUpdate(product, "", Timestamp.valueOf(createdTime));
        return cartMapper.insert(product.getNumIid(), createdTime, userId, quantity, properties, propertiesName) > 0;
    }

    @Override
    public Boolean delCart(CartDelDTO dto) {
        Boolean status = cartMapper.delete(dto.getUserId(), Integer.valueOf(dto.getCartId())) != 0;
        return status;
    }

    @Override
    public Boolean updateCart(CartUpdateQuantityDTO dto) {
        CartUpdateQuantity cart = CartUpdateQuantity.builder().build();
        BeanUtils.copyProperties(dto, cart);
        Boolean status = cartMapper.updateQuantityById(cart) != 0;
        return status;
    }

    @Override
    public CartItemVO getCartByProduct(String numIid, String userId, String propertiesName) {
        CartItem cartItem = cartMapper.selectAllByUserId(userId, numIid, propertiesName);
        if (cartItem == null) return null;
        List<CartItemVO> result = new ArrayList<>();
        HashMap<String, TranslatorDict> tranDict = new HashMap<>();
        ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
        BeanUtils.copyProperties(cartItem, psdVO);
        CartItemVO cartItemVO = CartItemVO.builder()
                .cartId(cartItem.getCartId())
                .createdDate(cartItem.getCreatedDate())
                .productDetail(psdVO)
                .propertiesName(cartItem.getPropertiesName())
                .properties(cartItem.getProperties())
                .quantity(cartItem.getQuantity())
                .build();
        skuPropService.extractSkuToTranDict(cartItemVO, tranDict);
        translator.translatorCache(tranDict, TranslatorType.ZH2RU);
        return cartItemVO;
    }
}
