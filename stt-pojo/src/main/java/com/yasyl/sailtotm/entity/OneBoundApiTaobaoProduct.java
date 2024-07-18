package com.yasyl.sailtotm.entity;

import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OneBoundApiTaobaoProduct {
    private String detailUrl;
    private String numIid;
    private String picUrl;
    private String price;
    private String promotionPrice;
    private long sales;
    private String sellerNick;
    private String title;
    private String q;
    private LocalDateTime createdDate;
    private String sellerNickZh;
    private String titleZh;

    public OneBoundApiTaobaoProduct(Product item, String q, Timestamp createdDate) {
        setQ(q);
        setCreatedDate(createdDate.toLocalDateTime());
        BeanUtils.copyProperties(item, this);
    }
}
