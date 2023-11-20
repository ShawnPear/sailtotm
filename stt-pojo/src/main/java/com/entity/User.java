// User.java

package com.entity;

/**
 * User
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户创建时间
     */
    private String createdDate;
    /**
     * 生日
     */
    private String dateOfBirth;
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
    private String updatedDate;
    /**
     * 用户id
     */
    private long userid;
    /**
     * 用户名
     */
    private String userName;
}

// Name.java