package com.service.impl;

import com.component.TranslatorPost;
import com.entity.TranslatorDict;
import com.enumeration.TranslatorType;
import com.exception.user.TranslatorException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mapper.TranslatorDictMapper;
import com.properties.AzureTranslatorProperties;
import com.service.TranslatorService;
import com.utils.TranslatorUntil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.constant.MessageConstant.TRANSLATOR_ERROR;

@Service
public class TranslatorServiceImpl implements TranslatorService {
    @Autowired
    AzureTranslatorProperties translatorProperties;
    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private TranslatorPost translatorPost;
    @Autowired
    private TranslatorDictMapper translatorDictMapper;

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
    public String translator(String source, TranslatorType type) {
        List<String> list = new ArrayList<>();
        list.add(source);
        return translator(list, type).get(0);
    }

    @Override
    public Map<String, TranslatorDict> translator(Map<String, TranslatorDict> source, TranslatorType type) {
        Set<Map.Entry<String, TranslatorDict>> entries = source.entrySet();
        List<String> list = new ArrayList<>();
        if (type == TranslatorType.ZH2RU) {
            for (Map.Entry<String, TranslatorDict> me : entries) {
                list.add(me.getValue().getZh());
            }
        } else if (type == TranslatorType.RU2ZH) {
            for (Map.Entry<String, TranslatorDict> me : entries) {
                list.add(me.getValue().getRu());
            }
        }
        List<String> rawList = new ArrayList<>(list);
        list = translator(list, type);
        int iList = 0;
        if (type == TranslatorType.ZH2RU) {
            for (String raw : rawList) {
                source.get(raw).setRu(list.get(iList++));
            }
        } else if (type == TranslatorType.RU2ZH) {
            for (String raw : rawList) {
                source.get(raw).setZh(list.get(iList++));
            }
        }
        return source;
    }

    @Override
    public String translatorCache(String source, TranslatorType type) {
        if (type == TranslatorType.ZH2RU) {
            TranslatorDict byZh = translatorDictMapper.selectByZh(source);
            if (byZh == null) {
                String translator = translator(source, type);
                new Thread(() -> translatorDictMapper.insert(TranslatorDict.builder().zh(source).ru(translator).build())).start();
                return translator;
            }
            return byZh.getRu();
        } else if (type == TranslatorType.RU2ZH) {
            TranslatorDict byRu = translatorDictMapper.selectByZh(source);
            if (byRu == null) {
                String translator = translator(source, type);
                new Thread(() -> translatorDictMapper.insert(TranslatorDict.builder().ru(source).zh(translator).build())).start();
                return translator;
            }
            return byRu.getZh();
        }
        return null;
    }

}
