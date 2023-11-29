// ProductDetail.java

package com.vo;

import lombok.Builder;

@lombok.Data
@Builder
public class ProductSimpleDetailVO {
    private String picUrl;
    private String numIid;
    private String promotionPrice;
    private String price;
    private String title;
}