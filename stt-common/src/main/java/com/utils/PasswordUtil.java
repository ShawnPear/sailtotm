package com.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class PasswordUtil {
    public static String getMD5Password(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }
}
