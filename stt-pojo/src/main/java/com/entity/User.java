// User.java

package com.entity;

/**
 * User
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户创建时间
     */
    private LocalDateTime createdDate;
    /**
     * 生日
     */
    private LocalDate dateOfBirth;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 真实姓名
     */
    private Name name;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 信息最后修改时间
     */
    private LocalDateTime updatedDate;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户名
     */
    private String userName;
}

// Name.java