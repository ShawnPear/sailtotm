package com.yasyl.sailtotm.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchItem {
    private Integer searchId;
    private String userId;
    private String search;
    private Integer searchedTimes;
    private String createdDate;
    private String updatedDate;
}
