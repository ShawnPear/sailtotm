package com.yasyl.sailtotm.vo.Cart;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartListPageVO {
    String page;
    String pageSize;
    List<CartItemVO> product_detail_list;
}
