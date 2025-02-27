package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.annotation.CheckUserId;
import com.yasyl.sailtotm.dto.OrderDTO;
import com.yasyl.sailtotm.dto.OrderListDTO;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.OrderService;
import com.yasyl.sailtotm.vo.Order.OrderBaseListPageVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.yasyl.sailtotm.constant.MessageConstant.*;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.CLASS;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.STRING;

@RestController
@RequestMapping("/user/order")
@Api(tags = "订单接口")
@Slf4j
public class OrderUserController {

    @Autowired
    OrderService service;

    @GetMapping("/{user_id}")
    @CheckUserId(STRING)
    public Result<OrderBaseListPageVO> getAllById(@PathVariable String user_id, String page, String page_size, String q,String status) {
        OrderBaseListPageVO listVO;
        if (q == null || q.isEmpty())
            listVO = service.getByIdPage(user_id, page, page_size,status);
        else
            listVO = service.getByIdPageQ(user_id, page, page_size, q,status);
        return Result.dataDetect(!listVO.getOrder_detail_list().isEmpty(), SUCCESS, NO_DATA, listVO);
    }

    @PostMapping
    @CheckUserId(CLASS)
    @Transactional
    public Result<List<Integer>> submitOrder(@RequestBody OrderListDTO dto) {
        Integer cnt = Integer.valueOf(dto.getOrderCnt());
        Boolean status = true;
        List<Integer> orderIdList = new ArrayList<>();
        for (Integer i = 0; i < cnt; i++) {
            OrderDTO order = dto.getOrderList().get(i);
            orderIdList.add(service.submitOrder(order));
        }
        return Result.status(status, SUCCESS, FAIL, orderIdList);
    }

    @DeleteMapping
    @CheckUserId(STRING)
    public Result cancelOrder(String user_id, String order_id) {
        Boolean status = service.cancelNoPayOrder(user_id, order_id);
        return Result.status(status);
    }
}
