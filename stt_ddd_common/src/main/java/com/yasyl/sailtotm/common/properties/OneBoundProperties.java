package com.yasyl.sailtotm.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shawn
 */
@Component
@ConfigurationProperties(prefix = "onebound")
@Data
public class OneBoundProperties {
    private String key;
    private String secret;

    /*
     * 万邦的api网址
     * */
    private String host;

    /*
     * 淘宝搜索path
     * */
    private String taobaoSearch;
    private String taobaoSearchDetail;
    private String scheme;
}
