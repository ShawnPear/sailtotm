package com.utils;

import java.time.LocalDateTime;

public class TimeUtil {
    public static LocalDateTime getLocalDateTime() {
        LocalDateTime now = LocalDateTime.now().withNano(0);
        return now;
    }
}
