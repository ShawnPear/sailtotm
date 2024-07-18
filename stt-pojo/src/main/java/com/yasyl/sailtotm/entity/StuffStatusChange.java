package com.yasyl.sailtotm.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StuffStatusChange {
    private Integer statusId;
    private Integer roleId;
    private Integer locationId;
    private Integer stuffId;
}
