package com.yasyl.sailtotm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreLocationVO {
    public Integer locationId;
    public String location;
    private String address;
    private String contact;
    private String worktime;
}
