package com.yasyl.sailtotm.dto.Stuff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StuffStatusChangeDTO {
    private Integer statusId;
    private Integer roleId;
    private Integer locationId;
    private String stuffId;
}
