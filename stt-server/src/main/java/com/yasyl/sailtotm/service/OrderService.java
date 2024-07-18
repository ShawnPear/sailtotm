package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.OrderDTO;
import com.yasyl.sailtotm.dto.PayDTO;
import com.yasyl.sailtotm.vo.Order.OrderBaseListPageVO;
import com.yasyl.sailtotm.vo.Order.OrderExtraInfoVO;
import com.yasyl.sailtotm.vo.Order.OrderExtraListPageVO;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    @Transactional
    public Integer submitOrder(OrderDTO dto);

    OrderBaseListPageVO getByIdPage(String userId, String page, String pageSize,String status);

    OrderBaseListPageVO getByIdPageQ(String userId, String page, String pageSize, String q,String status);

    Boolean cancelNoPayOrder(String userId, String orderId);

    public OrderExtraInfoVO getByOrderId(String orderId);

    OrderExtraListPageVO getExtraByUserId(String userId, String page, String pageSize);

    @Transactional
    Boolean pay(PayDTO dto);

    @Transactional
    Boolean beginOrderTransactional(String orderId, String userId, String stuffId);
}
