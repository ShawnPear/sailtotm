package com.yasyl.sailtotm.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransportType {
    private Integer transportId;
    private String transport;
    private Double price;
    private LocalDateTime updatedDate;
}
