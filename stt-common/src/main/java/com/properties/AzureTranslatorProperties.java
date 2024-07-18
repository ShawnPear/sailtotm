package com.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "translator.azure-translator")
@Data
public class AzureTranslatorProperties {
    private String host;
    private String translator;
    private String china;
    private String russia;
    private String OcpApimSubscriptionKey1;
    private String OcpApimSubscriptionKey2;
    private String OcpApimSubscriptionRegion;
    private String apiVersion;
    private String scheme;
}
