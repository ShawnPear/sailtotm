package com.service.impl;

import com.constant.MessageConstant;
import com.dto.TaobaoSearchDTO;
import com.dto.TaobaoSearchDetailDTO;
import com.entity.TaobaoGoodList.Product;
import com.exception.user.OneBoundApiException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mapper_helper.OneBoundApiTaobaoProductMapperHelper;
import com.properties.OneBoundProperties;
import com.service.OneBoundApiService;
import com.utils.GetUri;
import com.utils.TimeUtil;
import com.vo.TaobaoGood.TaobaoGoodDetailVO;
import com.vo.TaobaoGood.TaobaoGoodListVO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OneBoundApiServiceImpl implements OneBoundApiService {

    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    private OneBoundProperties obProperties;

    @Autowired
    private OneBoundApiTaobaoProductMapperHelper obApiTaobaoProductMapperHelper;

    @Autowired
    private CloseableHttpClient httpClient;

    @Override
    public TaobaoGoodListVO taoBaoSearch(TaobaoSearchDTO dto) {
        String keyWord = dto.getQ();

        Map<String, Object> query = new HashMap<>();
        query.put("q", keyWord);
        query.put("start_price", dto.getStartPrice());
        query.put("end_price", dto.getEndPrice());
        query.put("page", dto.getPage());
        query.put("cat", dto.getCat());
        query.put("sort", null);
        query.put("key", obProperties.getKey());
        query.put("secret", obProperties.getSecret());
        String uri = GetUri.builder()
                .scheme(obProperties.getScheme())
                .host(obProperties.getHost())
                .path(obProperties.getTaobaoSearch())
                .query(query)
                .build().toString();        //创建请求对象
        HttpGet httpGet = new HttpGet(uri);
        try {
            //发送请求，接受响应结果
            CloseableHttpResponse response = httpClient.execute(httpGet);

            //获取服务端返回的状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("服务端返回的状态码为：" + statusCode);

            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);
            TaobaoGoodListVO goodList = jsonMapper.readValue(body, TaobaoGoodListVO.class);
            //缓存到数据库去
            List<Product> itemList = goodList.getItems().getItem();
            for (Product item : itemList) {
                new Thread(() -> {
                    Timestamp time = Timestamp.valueOf(TimeUtil.getLocalDateTime());
                    obApiTaobaoProductMapperHelper.insertOrUpdate(item, dto.getQ(), time);
                }).start();
            }

            return goodList;
        } catch (IOException e) {
            throw new OneBoundApiException(MessageConstant.OneBoundApi_SEARCH_ERROR);
        }
    }

    @Override
    public TaobaoGoodDetailVO taoBaoSearchDetail(TaobaoSearchDetailDTO dto) throws OneBoundApiException {
        String numIid = dto.getNumIid();

        Map<String, Object> query = new HashMap<>();
        query.put("num_iid", numIid);
        query.put("is_promotion", 1);
        query.put("lang", "zh-CN");
        query.put("key", obProperties.getKey());
        query.put("secret", obProperties.getSecret());
        String uri = GetUri.builder()
                .scheme(obProperties.getScheme())
                .host(obProperties.getHost())
                .path(obProperties.getTaobaoSearchDetail())
                .query(query)
                .build().toString();
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

            return TaobaoGoodDetailVO.builder()
                    .detailJson(body)
                    .build();
        } catch (IOException e) {
            throw new OneBoundApiException(MessageConstant.OneBoundApi_SEARCH_ERROR);
        }
    }
}
