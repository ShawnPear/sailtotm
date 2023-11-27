package com.controller.user;

import com.dto.BrowserHistoryDTO;
import com.entity.TaobaoGoodList.Product;
import com.result.PageResult;
import com.result.Result;
import com.service.BrowserHistoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/history")
@Api(tags = "历史记录操作接口")
@Slf4j
public class BrowserHistoryController {
    @Autowired
    private BrowserHistoryService browserHistoryService;

    @GetMapping("/{user_id}")
    public Result<PageResult> getBrowserHistory(@PathVariable String user_id, String page, String page_size) {
        PageResult pageResult = browserHistoryService.getBrowserHistory(user_id, page, page_size);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result addBrowserHistory(BrowserHistoryDTO dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        browserHistoryService.addBrowserHistory(product, dto.getUserId());
        return Result.success();
    }
}
