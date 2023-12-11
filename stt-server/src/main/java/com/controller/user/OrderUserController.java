package com.controller.user;

import com.annotation.CheckUserId;
import com.dto.OrderDTO;
import com.dto.OrderListDTO;
import com.result.Result;
import com.service.OrderService;
import com.vo.Order.OrderBaseListPageVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.constant.MessageConstant.NO_DATA;
import static com.constant.MessageConstant.SUCCESS;
import static com.enumeration.UserIdIntoType.CLASS;
import static com.enumeration.UserIdIntoType.STRING;

@RestController
@RequestMapping("/user/order")
@Api(tags = "订单接口")
@Slf4j
public class OrderUserController {

    @Autowired
    OrderService service;

    @GetMapping("/{user_id}")
    @CheckUserId(STRING)
    public Result<OrderBaseListPageVO> getAllById(@PathVariable String user_id, String page, String page_size, String q) {
        OrderBaseListPageVO listVO;
        if (q == null || q.isEmpty())
            listVO = service.getByIdPage(user_id, page, page_size);
        else
            listVO = service.getByIdPageQ(user_id, page, page_size, q);
        return Result.dataDetect(!listVO.getOrder_detail_list().isEmpty(), SUCCESS, NO_DATA, listVO);
    }

    @PostMapping
    @CheckUserId(CLASS)
    @Transactional
    public Result submitOrder(@RequestBody OrderListDTO dto) {
        Integer cnt = Integer.valueOf(dto.getOrderCnt());
        Boolean status = true;
        for (Integer i = 0; i < cnt; i++) {
            OrderDTO order = dto.getOrderList().get(i);
            status &= service.submitOrder(order);
        }
        return Result.status(status);
    }

    @DeleteMapping
    @CheckUserId(STRING)
    public Result cancelOrder(String user_id, String order_id) {
        Boolean status = service.cancelNoPayOrder(user_id, order_id);
        return Result.status(status);
    }
}
