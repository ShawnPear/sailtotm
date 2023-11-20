package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 真实姓名
 */
@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    private String firstName;
    private String lastName;
}
