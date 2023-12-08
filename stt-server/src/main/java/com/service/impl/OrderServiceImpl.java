package com.service.impl;

import com.dto.OrderDTO;
import com.dto.PayDTO;
import com.entity.Order.Good.GoodDetail;
import com.entity.Order.Order;
import com.entity.Order.Pay.PayHistory;
import com.entity.Order.Transport.TransportDetail;
import com.entity.OrderBaseInfo;
import com.entity.OrderExtraInfo;
import com.entity.OrderStatusChange;
import com.entity.PaySum;
import com.entity.TaobaoGoodList.Product;
import com.enumeration.OrderStatusType;
import com.enumeration.PayOutOrInType;
import com.enumeration.PayType;
import com.exception.user.OrderException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.GoodDetailMapper;
import com.mapper.OrderMapper;
import com.mapper.PayMapper;
import com.mapper.TransportDetailMapper;
import com.mapper.mapper_helper.OneBoundApiTaobaoProductMapperHelper;
import com.mapper.mapper_helper.PickUpBaseMapperHelper;
import com.service.OrderService;
import com.vo.Order.OrderBaseInfoVO;
import com.vo.Order.OrderBaseListPageVO;
import com.vo.Order.OrderExtraInfoVO;
import com.vo.Order.OrderExtraListPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.constant.MessageConstant.*;
import static com.enumeration.OrderStatusType.NoPay;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OneBoundApiTaobaoProductMapperHelper productMapperHelper;

    @Autowired
    GoodDetailMapper goodDetailMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PayMapper payMapper;

    @Autowired
    TransportDetailMapper transportDetailMapper;

    @Autowired
    PickUpBaseMapperHelper pickUpBaseMapperHelper;


    @Override
    public Boolean submitOrder(OrderDTO dto) {
        try {
            Integer quantity = Integer.valueOf(dto.getQuantity());
            Double expectPaySum = Double.valueOf(dto.getPromotionPrice()) * quantity;

            Product product = Product.builder().build();
            BeanUtils.copyProperties(dto, product);

            GoodDetail goodDetail = GoodDetail.builder().quantity(quantity).numIid(product.getNumIid()).propertiesName(dto.getPropertiesName()).properties(dto.getProperties()).build();
            TransportDetail transportDetail = TransportDetail.builder().transportStatus(0).locationId(Integer.valueOf(dto.getLocation())).transportId(Integer.valueOf(dto.getTransportType())).build();
            PaySum paySum = PaySum.builder().payNow(0.0).payExpect(expectPaySum).createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).build();

            Boolean status = productMapperHelper.insertOrUpdate(product, "", Timestamp.valueOf(LocalDateTime.now()));
            status &= goodDetailMapper.insertGoodDetail(goodDetail);
            status &= payMapper.generatePaySum(paySum);
            status &= transportDetailMapper.add(transportDetail);

            Order order = Order.builder()
                    .userId(Integer.valueOf(dto.getUserId()))
                    .goodDetailId(goodDetail.getGoodDetailId())
                    .transportDetailId(transportDetail.getTransportDetailId())
                    .paySumId(paySum.getPaySumId())
                    .statusId(NoPay)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            status &= orderMapper.submit(order);

            if (!status) throw new SQLException();
            return status;
        } catch (Exception e) {
            throw new OrderException(ORDER_SUBMIT_ERROR);
        }
    }

    @Override
    public OrderBaseListPageVO getByIdPage(String userId, String page, String pageSize) {
        OrderBaseListPageVO vo = OrderBaseListPageVO.builder().page(page).pageSize(pageSize).build();
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<OrderBaseInfo> pageList = orderMapper.getByIdPage(userId);
        List<OrderBaseInfoVO> list = new ArrayList<>();
        for (OrderBaseInfo order : pageList.getResult()) {
            list.add(new OrderBaseInfoVO(order));
        }
        vo.setOrder_detail_list(list);
        return vo;
    }

    @Override
    public OrderBaseListPageVO getByIdPageQ(String userId, String page, String pageSize, String q) {
        OrderBaseListPageVO vo = OrderBaseListPageVO.builder().page(page).pageSize(pageSize).build();
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<OrderBaseInfo> pageList = orderMapper.getByIdPageQ(userId, "%" + q + "%");
        List<OrderBaseInfoVO> list = new ArrayList<>();
        for (OrderBaseInfo order : pageList.getResult()) {
            list.add(new OrderBaseInfoVO(order));
        }
        vo.setOrder_detail_list(list);
        return vo;
    }

    @Override
    public Boolean cancelNoPayOrder(String userId, String orderId) {
        Order order = orderMapper.getOrder(userId, orderId);
        if (order == null) {
            throw new OrderException(NO_ORDER);
        }
        if (order.getStatusId() != NoPay) {
            throw new OrderException(CANT_CANCEL_PAY_ORDER);
        }
        return orderMapper.cancelOrder(orderId);
    }

    @Override
    public OrderExtraInfoVO getByOrderId(String orderId) {
        OrderExtraInfo orderInfo = orderMapper.getOrderInfoByOrderId(orderId);
        return new OrderExtraInfoVO(orderInfo);
    }

    @Override
    public OrderExtraListPageVO getExtraByUserId(String userId, String page, String pageSize) {
        OrderExtraListPageVO vo = OrderExtraListPageVO.builder().page(page).pageSize(pageSize).build();
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<OrderExtraInfo> pageList = orderMapper.getOrderInfoByUserId(userId);
        List<OrderExtraInfoVO> list = new ArrayList<>();
        for (OrderExtraInfo order : pageList.getResult()) {
            list.add(new OrderExtraInfoVO(order));
        }
        vo.setOrder_detail_list(list);
        return vo;
    }

    @Override
    public Boolean pay(PayDTO dto) {
        String userId = dto.getUserId();
        String orderId = dto.getOrderId();
        String actualPay = dto.getActualPay();
        String stuffId = dto.getStuffId();
//        添加
        Order order = orderMapper.getOrder(userId, orderId);
        Integer paySumId = order.getPaySumId();

        PayHistory newPay = PayHistory.builder()
                .payAmount(Double.valueOf(actualPay))
                .payOutOrIn(PayOutOrInType.INCOME)
                .stuffId(Integer.valueOf(stuffId))
                .paySumId(paySumId)
                .payType(PayType.OFFLINE)
                .createdDate(LocalDateTime.now())
                .build();
        Boolean status = payMapper.insertPayHistory(newPay);
        status &= payMapper.checkPaySum(paySumId, LocalDateTime.now());
        return status;
    }

    @Override
    public Boolean beginOrderTransactional(String orderId, String userId, String stuffId) {
        Integer newStatus = OrderStatusType.CnWaitBuy;
        Order order = orderMapper.getOrder(userId, orderId);
        Boolean status = orderMapper.addStatusChangeHistory(OrderStatusChange.builder()
                .stuffId(Integer.valueOf(stuffId))
                .orderId(Integer.valueOf(orderId))
                .createdDate(LocalDateTime.now())
                .oldStatus(order.getStatusId())
                .newStatus(newStatus)
                .build());
        status &= orderMapper.setOrderStatus(orderId, newStatus, LocalDateTime.now());
        Integer pickupCode = pickUpBaseMapperHelper.usePickUpCode();
        status &= orderMapper.loadPickupCode(orderId, pickupCode);
        return status;
    }
}
