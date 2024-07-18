package com.yasyl.sailtotm.vo;

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
public class UserVO {
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
    private String firstName;
    private String lastName;
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

    private Boolean enable;
}
