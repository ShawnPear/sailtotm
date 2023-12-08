package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderBaseInfo {
    private String orderId;
    private Integer status;
    private String createdDate;
    private Double totalPrice;
    private String paidDate;
    private String locationId;
    private String numIid;
    private String picUrl;
    private String promotionPrice;
    private String price;
    private String title;
    private String properties;
    private String propertiesName;
    private Integer quantity;
    private String transportPrice;
    private Integer transportType;
    private String estimatedTransportPrice;
}
