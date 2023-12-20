package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stuff {
    private LocalDateTime createdDate;
    private String email;
    private long locationId;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private LocalDateTime resignDate;
    private long roleId;
    private double salary;
    private long statusId;
    private long stuffId;
    private LocalDateTime updatedDate;
}