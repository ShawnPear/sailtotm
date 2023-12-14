package com.component;

import com.enumeration.TranslatorType;
import com.properties.AzureTranslatorProperties;
import com.utils.GetUri;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class TranslatorPost extends HttpPost {
    @Autowired
    AzureTranslatorProperties translatorProperties;

    private GetUri getUri;

    @PostConstruct
    public void init() {
        Map<String, Object> query = new HashMap<>();
        query.put("api-version", translatorProperties.getApiVersion());
        String uri = GetUri.builder()
                .scheme(translatorProperties.getScheme())
                .host(translatorProperties.getHost())
                .path(translatorProperties.getTranslator())
                .query(query)
                .build().toString();
        this.setURI(URI.create(uri));
        this.setHeader("Ocp-Apim-Subscription-Key", translatorProperties.getOcpApimSubscriptionKey1());
        this.setHeader("Ocp-Apim-Subscription-Region", translatorProperties.getOcpApimSubscriptionRegion());
        this.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
    }

    public void setTranslatorType(TranslatorType type) {
        Map<String, Object> query = new HashMap<>();
        query.put("api-version", translatorProperties.getApiVersion());
        if (type == TranslatorType.ZH2RU) {
            query.put("from", translatorProperties.getChina());
            query.put("to", translatorProperties.getRussia());
        } else if (type == TranslatorType.RU2ZH) {
            query.put("from", translatorProperties.getRussia());
            query.put("to", translatorProperties.getChina());
        }
        String uri = GetUri.builder()
                .scheme(translatorProperties.getScheme())
                .host(translatorProperties.getHost())
                .path(translatorProperties.getTranslator())
                .query(query)
                .build().toString();
        this.setURI(URI.create(uri));
    }
}
