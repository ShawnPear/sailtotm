package com.yasyl.sailtotm.infra.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: SailToTm
 * @description: 商品详情
 * @author: wujubin
 * @create: 2024-07-23 19:26
 **/
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaobaoGoodDetailDTO {
    private TaobaoGoodDetail item;
}

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class TaobaoGoodDetail {
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
    //    private List<Video> video;
    private String desc;
    private String descShort;
    private List<String> descImg;
}

// ItemImg.java

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ItemImg {
    private String url;
}

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class PropImgs {
    private List<PropImg> propImg;
}

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class PropImg {
    private String properties;
    private String url;
}

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Prop {
    private String name;
    private String value;
}

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Skus {
    private List<Sku> sku;
}

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Sku {
    private String orginalPrice;
    private String price;
    private String properties;
    private String propertiesName;
    private String quantity;
    private List<String> propertiesList;
    private List<PropertiesName> propertiesNameList;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class PropertiesName {
    private String properties;
    private TranslatorDict propertiesNameItem;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class TranslatorDict {
    private Integer translatorId;
    private String zh;
    private String ru;
}