package com.yasyl.sailtotm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "es")
@Data
public class EsProperties {
    private String OneBoundApiSearchDB;
}
