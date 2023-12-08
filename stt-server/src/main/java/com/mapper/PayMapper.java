package com.mapper;

import com.entity.Order.Pay.PayHistory;
import com.entity.PaySum;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface PayMapper {
    Boolean insertPayHistory(PayHistory pay);

    Boolean checkPaySum(Integer paySumId, LocalDateTime updatedDate);

    Boolean generatePaySum(PaySum paySum);
}
