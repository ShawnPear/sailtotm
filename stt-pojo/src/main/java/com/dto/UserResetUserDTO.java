package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResetUserDTO {
    private String userId;
    private LocalDate dateOfBirth;
    private String firstName;
    private String lastName;
    private String userName;
}
