package com.controller.user;

import com.annotation.CheckUserId;
import com.constant.MessageConstant;
import com.dto.BrowserHistory.BrowserHistoryDTO;
import com.entity.TaobaoGoodList.Product;
import com.enumeration.UserIdIntoType;
import com.result.Result;
import com.service.BrowserHistoryService;
import com.vo.BrowserHistory.BrowserHistoryListPageVO;
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
    @CheckUserId(UserIdIntoType.STRING)
    public Result<BrowserHistoryListPageVO> getBrowserHistory(@PathVariable String user_id, String page, String page_size, String q) {
        BrowserHistoryListPageVO result = BrowserHistoryListPageVO.builder().build();
        if (q == null || q.isEmpty()) {
            result = browserHistoryService.getBrowserHistory(user_id, page, page_size);
        } else {
            result = browserHistoryService.getBrowserHistoryBySearch(user_id, page, page_size, q);
        }
        return Result.success(result, MessageConstant.SUCCESS);
    }

    @PostMapping
    @CheckUserId(UserIdIntoType.CLASS)
    public Result addBrowserHistory(@RequestBody BrowserHistoryDTO dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        browserHistoryService.addBrowserHistory(product, dto.getUserId());
        return Result.success(MessageConstant.SUCCESS);
    }
}
