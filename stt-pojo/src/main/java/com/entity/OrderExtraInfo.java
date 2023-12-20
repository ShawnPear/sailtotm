package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderExtraInfo {
    private String orderId;
    private String picUrl;
    private String numIid;
    private String promotionPrice;
    private String price;
    private String title;
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
}
