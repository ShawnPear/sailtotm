// User.java

package com.yasyl.sailtotm.entity;

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
public class User {
    private long userId;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime createdDate;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private LocalDateTime updatedDate;
    private String userName;
    private Boolean enable;
}

// Name.java