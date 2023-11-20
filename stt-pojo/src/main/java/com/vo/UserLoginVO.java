package com.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    private String token;
    private String user_id;
    private String user_name;
}
