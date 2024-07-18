package com.yasyl.sailtotm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportDetailDTO {
    private String orderId;
    private String transportDetailId;
    private String stuffId;
    private String carrierId;
    private String transportId;
    private String locationId;
    private String width;
    private String height;
    private String length;
    private String weight;
    private String estimatedTransportCost;
    private String transportCost;
    private String transportStatus;
}
