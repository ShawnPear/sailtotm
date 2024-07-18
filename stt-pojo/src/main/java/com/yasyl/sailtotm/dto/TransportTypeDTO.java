package com.yasyl.sailtotm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportTypeDTO {
    private Integer transportId;
    private String transport;
    private Double price;
}
