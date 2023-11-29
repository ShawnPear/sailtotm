package com.dto.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResetPasswordDTO {
    private String newPassword;
    private String oldPassword;
    private String userId;
}
