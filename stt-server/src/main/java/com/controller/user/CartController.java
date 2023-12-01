package com.controller.user;

import com.constant.MessageConstant;
import com.dto.Cart.CartDTO;
import com.dto.Cart.CartDelDTO;
import com.dto.Cart.CartUpdateQuantityDTO;
import com.entity.TaobaoGoodList.Product;
import com.result.Result;
import com.service.CartService;
import com.vo.Cart.CartListPageVO;
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

    @GetMapping("/{user_id}")
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
    public Result addCart(@RequestBody CartDTO dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        Boolean status = cartService.addCart(product, dto.getUserId(), dto.getQuantity());
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.error(MessageConstant.FAIL);
        }
    }

    @DeleteMapping
    public Result deleteCart(@RequestBody CartDelDTO dto) {
        Boolean status = cartService.delCart(dto);
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.success(MessageConstant.WITHOUT_DATA_CANT_DELETE);
        }
    }

    @PatchMapping
    public Result updateCart(@RequestBody CartUpdateQuantityDTO dto) {
        Boolean status = cartService.updateCart(dto);
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.error(MessageConstant.FAIL);
        }
    }
}
