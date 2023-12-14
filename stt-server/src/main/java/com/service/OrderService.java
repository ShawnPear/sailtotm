package com.service;

import com.dto.OrderDTO;
import com.dto.PayDTO;
import com.entity.PropertiesName;
import com.vo.Order.OrderBaseListPageVO;
import com.vo.Order.OrderExtraInfoVO;
import com.vo.Order.OrderExtraListPageVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    @Transactional
    public Boolean submitOrder(OrderDTO dto);

    OrderBaseListPageVO getByIdPage(String userId, String page, String pageSize);

    OrderBaseListPageVO getByIdPageQ(String userId, String page, String pageSize, String q);

    Boolean cancelNoPayOrder(String userId, String orderId);

    public OrderExtraInfoVO getByOrderId(String orderId);

    OrderExtraListPageVO getExtraByUserId(String userId, String page, String pageSize);

    @Transactional
    Boolean pay(PayDTO dto);

    Boolean beginOrderTransactional(String orderId, String userId, String stuffId);

    String getProp2String(List<String> propertiesList);

    String getPropNameRu2Zh(List<PropertiesName> propertiesNameList);
}
