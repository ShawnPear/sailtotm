package com.vo.Order;

import com.entity.OrderBaseInfo;
import com.entity.OrderExtraInfo;
import com.vo.ProductSimpleDetailVO;
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

    public OrderBaseInfoVO(OrderExtraInfo extraData) {
        this.productDetail = new ProductSimpleDetailVO(extraData);
        BeanUtils.copyProperties(extraData, this);
    }

    public OrderBaseInfoVO(OrderBaseInfo data) {
        this.productDetail = new ProductSimpleDetailVO(data);
        BeanUtils.copyProperties(data, this);
    }

}