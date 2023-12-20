package com.properties;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AutowiredBean {
    @Autowired
    AzureTranslatorProperties translatorProperties;

    @Bean
    public JsonMapper jsonMapper() {
        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return jsonMapper;
    }

    @Bean()
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }
}
