package com.yasyl.sailtotm.client.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
   private String detailUrl;
   private String numIid;
   private String picUrl;
   private String price;
   private String promotionPrice;
   private long sales;
   private String sellerNick;
   private String title;
   private String sellerNickZh;
   private String titleZh;
}