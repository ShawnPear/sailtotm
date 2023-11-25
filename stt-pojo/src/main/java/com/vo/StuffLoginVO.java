package com.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StuffLoginVO {
    private String firstName;
    private String lastName;
    private String roleid;
    private String stuffid;
    private String token;
}
