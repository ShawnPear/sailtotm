package com.dto.Stuff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StuffLoginDTO {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
}
