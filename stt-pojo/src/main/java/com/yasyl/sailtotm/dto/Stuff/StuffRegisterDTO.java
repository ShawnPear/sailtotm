package com.yasyl.sailtotm.dto.Stuff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StuffRegisterDTO {
    /**
     * 邮箱
     */
    private String email;
    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;
    private String locationId;
    /**
     * password
     */
    private String password;
    private String phoneNumber;
    private String roleId;
    private String salary;
}
