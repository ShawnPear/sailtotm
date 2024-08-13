package com.yasyl.sailtotm.infra.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "translator.youdao-translator")
@Data
public class YoudaoTranslatorProp {
    private String YoudaoUrl;
    private String AppKey;
    private String AppSecret;
}
