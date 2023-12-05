package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDTO {
    private String newPassword;
    private String oldPassword;
    private String userId;
}