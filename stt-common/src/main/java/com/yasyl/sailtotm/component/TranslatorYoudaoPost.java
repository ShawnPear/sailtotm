package com.yasyl.sailtotm.component;

import com.yasyl.sailtotm.enumeration.TranslatorType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yasyl.sailtotm.properties.YoudaoTranslatorProperties;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
@Scope("prototype")
public class TranslatorYoudaoPost {

    @Autowired
    YoudaoTranslatorProperties properties;

    public static List<String> requestForHttp(String url, Map<String, String> params, String[] qArray) throws IOException {

        /** 创建HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /** httpPost */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key, value));
        }
        for (int i = 0; i < qArray.length; i++) {
            paramsList.add(new BasicNameValuePair("q", qArray[i]));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            Header[] contentType = httpResponse.getHeaders("Content-Type");
            /** 响应不是音频流，直接显示结果 */
            HttpEntity httpEntity = httpResponse.getEntity();
            String json = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
            List<String> list = extractTranslations(json);
            return list;
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
//                logger.info("## release resouce error ##" + e);
            }
        }
    }

    /**
     * 生成加密字段
     */
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String truncate(String[] qArray) {
        if (qArray == null) {
            return null;
        }
        String batchQStr = String.join("", qArray);
        int len = batchQStr.length();
        return len <= 20 ? batchQStr : (batchQStr.substring(0, 10) + len + batchQStr.substring(len - 10, len));
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        List<String> q = new ArrayList<>();
        q.add("待输入的文字1");
        q.add("待输入的文字2");
        q.add("待输入的文字3");
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("from", "zh-CHS");
        params.put("to", "ru");
        params.put("signType", "v3");
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = "597345f5fb02a536" + truncate(q.toArray(new String[0])) + salt + curtime + "4VDJUrAfatUYAvbKA0vr8I72XqwWxZiT";
        String sign = getDigest(signStr);
        params.put("appKey", "597345f5fb02a536");
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("vocabId", "您的用户词表ID");
        /** 处理结果 */
        List<String> list = requestForHttp("https://openapi.youdao.com/v2/api", params, q.toArray(new String[0]));
        System.out.println(list);
    }

    private static List<String> extractTranslations(String jsonString) {
        List<String> translations = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode translateResultsNode = rootNode.get("translateResults");

            if (translateResultsNode != null && translateResultsNode.isArray()) {
                for (JsonNode resultNode : translateResultsNode) {
                    JsonNode translationNode = resultNode.get("translation");
                    if (translationNode != null && translationNode.isTextual()) {
                        translations.add(translationNode.asText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return translations;
    }

    public List<String> translator(TranslatorType type, String[] qArray) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
//        String[] qArray = {"待输入的文字1", "待输入的文字2", "待输入的文字3"};
        String salt = String.valueOf(System.currentTimeMillis());
        if (type == TranslatorType.RU2ZH) {
            params.put("from", "auto");
            params.put("to", "zh-CHS");
        } else {
            params.put("from", "zh-CHS");
            params.put("to", "ru");
        }
        params.put("signType", "v3");
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = properties.getAppKey() + truncate(qArray) + salt + curtime + properties.getAppSecret();
        String sign = getDigest(signStr);
        params.put("appKey", properties.getAppKey());
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("vocabId", "您的用户词表ID");
        List<String> list = requestForHttp(properties.getYoudaoUrl(), params, qArray);
        return list;
    }
}
