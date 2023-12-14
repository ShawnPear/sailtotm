// TaobaoGoodDetail.java

package com.entity.TaobaoGoodList.TaobaoGoodDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaobaoGoodDetail {
    private String detailUrl;
    private String emsFee;
    private String expressFee;
    private List<ItemImg> itemImgs;
    private String numIid;
    private String orginalPrice;
    private String postFee;
    private String price;
    private PropImgs propImgs;
    private List<Prop> props;
    private Object propsImg;
    private Object propsList;
    private Skus skus;
    private String title;
    private Video video;
    private String desc;
    private String descShort;
    private List<String> descImg;
}













