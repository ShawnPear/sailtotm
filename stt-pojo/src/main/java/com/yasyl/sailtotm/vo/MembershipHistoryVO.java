package com.yasyl.sailtotm.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MembershipHistoryVO {
    private String statusId;
    private String change;
    private String createdDate;
}
