package com.yasyl.sailtotm.vo.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderExtraListPageVO {
    String page;
    String pageSize;
    List<OrderExtraInfoVO> order_detail_list;
}
