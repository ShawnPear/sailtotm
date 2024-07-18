package com.yasyl.sailtotm.dto.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
}
