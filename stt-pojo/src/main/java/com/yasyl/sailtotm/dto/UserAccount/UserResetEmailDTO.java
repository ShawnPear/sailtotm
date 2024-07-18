package com.yasyl.sailtotm.dto.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResetEmailDTO {
    private String newEmail;
    private String userId;
}
