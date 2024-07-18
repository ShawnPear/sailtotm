package com.controller.admin;

import com.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/get-progress")
@Api(tags = "获取软件数据操作接口")
@Slf4j
public class GetProgressController {
    @GetMapping("/new-users-number")
    @ApiOperation("获取新注册的人数")
    public Result getNewUserNumber(){
        return Result.success();
    }

    @GetMapping("/new-orders-number")
    @ApiOperation("获取新注册的人数")
    public Result getNewUserOrders() {
        return Result.success();
    }

    @GetMapping("/active-users")
    @ApiOperation("获取活跃用户的人数")
    public Result getActiveUsers() {
        return Result.success();
    }

    @GetMapping("/search-number")
    @ApiOperation("获取搜索")
    public Result getSearchNumber() {
        return Result.success();
    }

    @GetMapping("/search-items-number")
    @ApiOperation("获取搜索详情页的人数")
    public Result getSearchItemsNumber() {
        return Result.success();
    }

    @GetMapping("/new-membership-users")
    @ApiOperation("获取搜索详情页的人数")
    public Result getNewMembership() {
        return Result.success();
    }

    @GetMapping("/total-order-sum")
    @ApiOperation("获取搜索详情页的人数")
    public Result getTotalOrderSum() {
        return Result.success();
    }

}
