package com.mapper;

import com.entity.Order.Order;
import com.entity.OrderBaseInfo;
import com.entity.OrderExtraInfo;
import com.entity.OrderStatusChange;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface OrderMapper {

    Boolean submit(Order order);

    Page<OrderBaseInfo> getByIdPage(String userId);

    Page<OrderBaseInfo> getByIdPageQ(String userId, String q);

    @Select("SELECT * from sailtotmdb.Orders where user_id = #{userId} and order_id = #{orderId}")
    Order getOrder(String userId, String orderId);

    @Update("update sailtotmdb.Orders set status_id = 0 where order_id = #{orderId}")
    Boolean cancelOrder(String orderId);

    OrderExtraInfo getOrderInfoByOrderId(String orderId);

    Page<OrderExtraInfo> getOrderInfoByUserId(String userId);

    @Update("update sailtotmdb.Orders set status_id = #{status} , updated_date = #{updatedDate} where order_id = #{orderId}")
    Boolean setOrderStatus(String orderId, Integer status, LocalDateTime updatedDate);

    @Insert("insert into sailtotmdb.OrderStatusChangeHIstory (created_date, order_id, stuff_id, old_status, new_status) VALUES (#{createdDate},#{orderId},#{stuffId},#{oldStatus},#{newStatus})")
    Boolean addStatusChangeHistory(OrderStatusChange data);

    @Update("update sailtotmdb.Orders set pickup_code = #{pickupCode} where order_id = #{orderId} ")
    Boolean loadPickupCode(String orderId, Integer pickupCode);
}
