package com.yasyl.sailtotm.entity.Order.Transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportDetail {
    private Integer transportDetailId;
    private Integer stuffId;
    private Integer carrierId;
    private Integer transportId;
    private Integer locationId;
    private Integer width;
    private Integer height;
    private Integer length;
    private Double weight;
    private Double estimatedTransportCost;
    private Double transportCost;
    private Integer transportStatus;
    private LocalDateTime transportCostPaidDate;
    private LocalDateTime measureDate;
}
