package com.yasyl.sailtotm.domain.payment.entity;

/**
 * User
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
    /**
     * 用户名
     */
    private long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 姓氏
     */
    private String firstName;

    /**
     * 名称
     */
    private String lastName;

    /**
     * 密码（密文）
     */
    private String password;
    
    /**
     * 生日
     */
    private LocalDate dateOfBirth;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 更新时间
     */
    private LocalDateTime updatedDate;

    /**
     * 创建时间
     */
    private LocalDateTime createdDate;

    /**
     * 账户是否生效（邮箱验证后生效）
     */
    private Boolean enable;
}