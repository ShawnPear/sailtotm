package com.controller.user;

import com.result.PageResult;
import com.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/cart")
@Api(tags = "购物车操作接口")
@Slf4j
public class CartController {

    @GetMapping("/{user_id}")
    public Result<PageResult> getByUserId(@PathVariable String user_id) {
        return null;
    }
}
