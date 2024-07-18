package com.service.impl.TranslatorImpl;

import com.component.TranslatorMicrosoftPost;
import com.entity.TranslatorDict;
import com.enumeration.TranslatorType;
import com.exception.user.TranslatorException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.properties.AzureTranslatorProperties;
import com.properties.TranslatorProperties;
import com.service.TranslatorService;
import com.utils.TranslatorUntil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.constant.MessageConstant.TRANSLATOR_ERROR;

@Service
@ConditionalOnProperty(name = TranslatorProperties.YML_TRANSLATOR_USE, havingValue = TranslatorProperties.AZURE)
public class TranslatorMicrosoft extends TranslatorService {
    @Autowired
    AzureTranslatorProperties translatorProperties;
    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private TranslatorMicrosoftPost translatorPost;

    @Override
    public List<String> translator(List<String> source, TranslatorType type) {
        try {
            translatorPost.setTranslatorType(type);
            StringEntity entity = TranslatorUntil.getUTF8JSON(source);
            translatorPost.setEntity(entity);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(translatorPost);
            // 处理响应
            HttpEntity responseEntity = response.getEntity();
            String body = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            JsonNode jNode = jsonMapper.readTree(body);
            for (int i = 0; i < jNode.size(); i++) {
                String s = jNode.get(i).get("translations").get(0).get("text").asText();
                source.set(i, s);
            }
            return source;
        } catch (Exception e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }

    @Override
    public List<?> translator(List<?> source, TranslatorType type, Class<?> translatorType) {
        if (source == null || source.isEmpty()) return new ArrayList<>();
        List<String> source2 = new ArrayList<>();
        if (translatorType == String.class) {
            source2 = (List<String>) source;
        } else if (translatorType == TranslatorDict.class) {
            if (type == TranslatorType.ZH2RU) {
                for (Object o : source) {
                    TranslatorDict dict = (TranslatorDict) o;
                    source2.add(dict.getZh());
                }
            } else if (type == TranslatorType.RU2ZH) {
                for (Object o : source) {
                    TranslatorDict dict = (TranslatorDict) o;
                    source2.add(dict.getRu());
                }
            }
        }
        try {
            translatorPost.setTranslatorType(type);
            StringEntity entity = TranslatorUntil.getUTF8JSON(source2);
            translatorPost.setEntity(entity);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(translatorPost);
            // 处理响应
            HttpEntity responseEntity = response.getEntity();
            String body = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            JsonNode jNode = jsonMapper.readTree(body);
            for (int i = 0; i < jNode.size(); i++) {
                String s = jNode.get(i).get("translations").get(0).get("text").asText();
                source2.set(i, s);
            }
            if (translatorType == String.class) {
                return source2;
            } else if (translatorType == TranslatorDict.class) {
                if (type == TranslatorType.ZH2RU) {
                    for (int i = 0; i < source2.size(); i++) {
                        TranslatorDict dict = (TranslatorDict) source.get(i);
                        dict.setRu(source2.get(i));
                    }
                } else if (type == TranslatorType.RU2ZH) {
                    for (int i = 0; i < source2.size(); i++) {
                        TranslatorDict dict = (TranslatorDict) source.get(i);
                        dict.setZh(source2.get(i));
                    }
                }
                return source;
            }
        } catch (Exception e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
        return source;
    }


}
