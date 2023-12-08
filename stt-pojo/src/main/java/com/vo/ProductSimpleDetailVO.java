// ProductDetail.java

package com.vo;

import com.entity.OrderBaseInfo;
import com.entity.OrderExtraInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSimpleDetailVO {
    private String picUrl;
    private String numIid;
    private String promotionPrice;
    private String price;
    private String title;

    public ProductSimpleDetailVO(OrderExtraInfo extraData) {
        BeanUtils.copyProperties(extraData, this);
    }

    public ProductSimpleDetailVO(OrderBaseInfo data) {
        BeanUtils.copyProperties(data, this);
    }
}