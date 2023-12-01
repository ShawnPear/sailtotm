package com.vo.SearchHIstory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchItemVO {
    private String updatedDate;
    private String q;
}