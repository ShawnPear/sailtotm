package com.yasyl.sailtotm.dto;

import com.yasyl.sailtotm.entity.PropertiesName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String location;
    private String numIid;
    private String picUrl;
    private String price;
    private String promotionPrice;
    private String properties;
    private String propertiesName;
    private String quantity;
    private String title;
    private String transportType;
    private String userId;
    private List<String> propertiesList;
    private List<PropertiesName> propertiesNameList;
}
