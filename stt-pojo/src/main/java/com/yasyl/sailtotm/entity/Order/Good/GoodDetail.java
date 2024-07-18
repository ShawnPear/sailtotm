package com.yasyl.sailtotm.entity.Order.Good;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetail {
    private Integer goodDetailId;
    private Integer quantity;
    private String numIid;
    private String properties;
    private String propertiesName;
}
