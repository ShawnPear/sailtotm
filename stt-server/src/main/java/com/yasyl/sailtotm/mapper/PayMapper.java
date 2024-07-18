package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.Order.Pay.PayHistory;
import com.yasyl.sailtotm.entity.PaySum;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface PayMapper {
    Boolean insertPayHistory(PayHistory pay);

    Boolean checkPaySum(Integer paySumId, LocalDateTime updatedDate);

    Boolean generatePaySum(PaySum paySum);
}
