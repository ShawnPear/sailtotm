package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.annotation.CheckUserId;
import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.Cart.CartDTO;
import com.yasyl.sailtotm.dto.Cart.CartDelDTO;
import com.yasyl.sailtotm.dto.Cart.CartUpdateQuantityDTO;
import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.yasyl.sailtotm.enumeration.UserIdIntoType;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.CartService;
import com.yasyl.sailtotm.service.SkuPropService;
import com.yasyl.sailtotm.vo.Cart.CartItemVO;
import com.yasyl.sailtotm.vo.Cart.CartListPageVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/cart")
@Api(tags = "购物车操作接口")
@Slf4j
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    SkuPropService skuPropService;

    @GetMapping("/{user_id}")
    @CheckUserId(UserIdIntoType.STRING)
    public Result<CartListPageVO> getByUserId(@PathVariable String user_id, String page, String pageSize, String q) {
        CartListPageVO result = CartListPageVO.builder().build();
        if (q == null || q.isEmpty()) {
            result = cartService.getCart(user_id, page, pageSize);
        } else {
            result = cartService.getCartBySearch(user_id, page, pageSize, q);
        }
        if (!result.getProduct_detail_list().isEmpty()) {
            return Result.success(result, MessageConstant.SUCCESS);
        } else {
            return Result.success(result, MessageConstant.NO_DATA);
        }
    }

    @PostMapping
    @CheckUserId(UserIdIntoType.CLASS)
    public Result addCart(@RequestBody CartDTO dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        dto.setProperties(skuPropService.getProp2String(dto.getPropertiesList()));
        dto.setPropertiesName(skuPropService.getPropNameRu2Zh(dto.getPropertiesNameList()));
        CartItemVO cartByProduct = cartService.getCartByProduct(dto.getNumIid(), dto.getUserId(), dto.getPropertiesName());
        Boolean status;
        if (cartByProduct == null) {
            status = cartService.addCart(product, dto.getUserId(), dto.getQuantity(), dto.getProperties(), dto.getPropertiesName());
        } else {
            status = cartService.updateCart(CartUpdateQuantityDTO.builder()
                    .cartId(String.valueOf(cartByProduct.getCartId()))
                    .quantity(cartByProduct.getQuantity() + dto.getQuantity())
                    .userId(dto.getUserId())
                    .build());
        }
        return Result.status(status);
    }

    @DeleteMapping
    @CheckUserId(UserIdIntoType.CLASS)
    public Result deleteCart(@RequestBody CartDelDTO dto) {
        Boolean status = cartService.delCart(dto);
        return Result.status(status);
    }

    @PatchMapping
    @CheckUserId(UserIdIntoType.CLASS)
    public Result updateCart(@RequestBody CartUpdateQuantityDTO dto) {
        Boolean status = cartService.updateCart(dto);
        return Result.status(status);
    }
}
