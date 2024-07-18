package com.yasyl.sailtotm.vo.Order;

import com.yasyl.sailtotm.entity.OrderBaseInfo;
import com.yasyl.sailtotm.entity.OrderExtraInfo;
import com.yasyl.sailtotm.vo.ProductSimpleDetailVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderBaseInfoVO {
    private String orderId;
    private ProductSimpleDetailVO productDetail;
    private Integer status;
    private String createdDate;
    private Double totalPrice;
    private String paidDate;
    private String locationId;
    private String properties;
    private String propertiesName;
    private Integer quantity;
    private String transportPrice;
    private String estimatedTransportPrice;
    private Integer transportType;
    private String pickupCode;
    private String locationAddress;
    private String locationContact;
    private String locationWorktime;

    public OrderBaseInfoVO(OrderExtraInfo extraData) {
        this.productDetail = new ProductSimpleDetailVO(extraData);
        BeanUtils.copyProperties(extraData, this);
    }

    public OrderBaseInfoVO(OrderBaseInfo data) {
        this.productDetail = new ProductSimpleDetailVO(data);
        BeanUtils.copyProperties(data, this);
    }

}