package com.yasyl.sailtotm.vo.Order;

import com.yasyl.sailtotm.entity.OrderExtraInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderExtraInfoVO {
    private OrderBaseInfoVO orderBaseInfo;
    //管理员相关的数据
    private Integer pickupCode;
    private String width;
    private String length;
    private String height;
    private String weight;
    private String userId;
    private String userName;
    private String locationAddress;
    private String locationContact;
    private String locationWorktime;

    public OrderExtraInfoVO(OrderExtraInfo extraData) {
        this.setOrderBaseInfo(new OrderBaseInfoVO(extraData));
        BeanUtils.copyProperties(extraData, this);
    }
}
