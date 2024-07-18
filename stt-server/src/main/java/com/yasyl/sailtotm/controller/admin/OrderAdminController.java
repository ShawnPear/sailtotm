package com.yasyl.sailtotm.controller.admin;

import com.yasyl.sailtotm.dto.PayDTO;
import com.yasyl.sailtotm.enumeration.PayType;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.OrderService;
import com.yasyl.sailtotm.vo.Order.OrderExtraInfoVO;
import com.yasyl.sailtotm.vo.Order.OrderExtraListPageVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.yasyl.sailtotm.constant.MessageConstant.SUCCESS;

@RestController
@RequestMapping("/admin/order")
@Api(tags = "订单接口")
@Slf4j
public class OrderAdminController {

    @Autowired
    OrderService service;

    @GetMapping("/order_id/{order_id}")
    public Result<OrderExtraInfoVO> getOrderByOrderId(@PathVariable String order_id) {
        OrderExtraInfoVO vo = service.getByOrderId(order_id);
        return Result.success(vo, SUCCESS);
    }

    @GetMapping("/user_id/{user_id}")
    public Result<OrderExtraListPageVO> getOrderByOrderId(@PathVariable String user_id, String page, String page_size) {
        OrderExtraListPageVO vo = service.getExtraByUserId(user_id, page, page_size);
        return Result.success(vo, SUCCESS);
    }

    @PostMapping("/pay")
    public Result dealPay(@RequestBody PayDTO dto) {
        dto.setPayType(String.valueOf(PayType.OFFLINE));
        boolean status = service.pay(dto);
        return Result.success(status);
    }

    @PatchMapping
    public Result beginTransaction(@RequestBody PayDTO dto) {
        Boolean status = service.beginOrderTransactional(dto.getOrderId(), dto.getUserId(), dto.getStuffId());
        return Result.success(status);
    }
}
