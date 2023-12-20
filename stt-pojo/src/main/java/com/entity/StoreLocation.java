package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreLocation {
    private Integer locationId;
    private String location;
    private String address;
    private String contact;
    private String worktime;
}
