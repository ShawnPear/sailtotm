package com.yasyl.sailtotm.client.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shawn
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDTO {
    private String dataFrom;
    private List<ProductDTO> item;
    private long itemWeightUpdate;
    private String page;
    private long pageSize;
    private String pagecount;
    private String realTotalResults;
    private String totalResults;
}

