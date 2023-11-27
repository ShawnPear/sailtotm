package com.service.impl;

import com.constant.MessageConstant;
import com.dto.TaobaoSearchDTO;
import com.entity.TaobaoGood.TaobaoGoodList;
import com.exception.user.OneBoundApiException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.properties.OneBoundProperties;
import com.service.OneBoundApiService;
import com.utils.NetUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OneBoundApiServiceImpl implements OneBoundApiService {

    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    private OneBoundProperties obProperties;

    @Override
    public TaobaoGoodList taoBaoSearch(TaobaoSearchDTO dto) {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        Map<String, Object> query = new HashMap<>();
        query.put("q", dto.getQ());
        query.put("start_price", dto.getStartPrice());
        query.put("end_price", dto.getEndPrice());
        query.put("page", dto.getPage());
        query.put("cat", dto.getCat());
        query.put("sort", null);
        query.put("key", obProperties.getKey());
        query.put("secret", obProperties.getSecret());
        String uri = NetUtil.getFullPath(obProperties.getScheme(), obProperties.getHost(), obProperties.getTaobaoSearch(), query);
        //创建请求对象
        HttpGet httpGet = new HttpGet(uri);
        try {
            //发送请求，接受响应结果
            CloseableHttpResponse response = httpClient.execute(httpGet);

            //获取服务端返回的状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("服务端返回的状态码为：" + statusCode);

            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);

            //缓存到数据库去


            TaobaoGoodList goodList = jsonMapper.readValue(body, TaobaoGoodList.class);
            //关闭资源
            response.close();
            httpClient.close();

            return goodList;
        } catch (IOException e) {
            throw new OneBoundApiException(MessageConstant.OneBoundApi_SEARCH_ERROR);
        }
    }
}
