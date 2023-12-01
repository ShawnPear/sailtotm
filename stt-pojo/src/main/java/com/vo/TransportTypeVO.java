package com.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransportTypeVO {
    private Integer transport_id;
    private String transport;
    private Double price;
    private String updated_date;
}
