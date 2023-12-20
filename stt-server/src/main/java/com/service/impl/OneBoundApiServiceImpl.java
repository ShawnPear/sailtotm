package com.service.impl;

import com.constant.MessageConstant;
import com.dto.Search.TaobaoSearchDTO;
import com.dto.Search.TaobaoSearchDetailDTO;
import com.entity.TaobaoGoodList.Items;
import com.entity.TaobaoGoodList.Product;
import com.entity.TaobaoGoodList.TaobaoGoodDetail.Prop;
import com.entity.TaobaoGoodList.TaobaoGoodDetail.Sku;
import com.entity.TaobaoGoodList.TaobaoGoodDetail.TaobaoGoodDetail;
import com.entity.TranslatorDict;
import com.enumeration.TranslatorType;
import com.exception.user.OneBoundApiException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mapper.TranslatorDictMapper;
import com.mapper.mapper_helper.OneBoundApiTaobaoProductMapperHelper;
import com.properties.OneBoundProperties;
import com.service.OneBoundApiService;
import com.service.SkuPropService;
import com.service.TranslatorService;
import com.utils.GetUri;
import com.utils.TimeUtil;
import com.vo.TaobaoGood.TaobaoGoodDetailRawJsonVO;
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
import java.util.*;

@Service
public class OneBoundApiServiceImpl implements OneBoundApiService {

    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    TranslatorService translator;
    @Autowired
    private OneBoundProperties obProperties;
    @Autowired
    private OneBoundApiTaobaoProductMapperHelper obApiTaobaoProductMapperHelper;

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private TranslatorDictMapper dictMapper;

    @Autowired
    private SkuPropService skuPropService;

    @Override
    public TaobaoGoodListVO taoBaoSearch(TaobaoSearchDTO dto) {
        String keyWord = dto.getQ();
        /*
         * 从数据库中取缓存的数据
         * */
        List<Product> products = obApiTaobaoProductMapperHelper.selectByQ(dto.getQ(), dto.getPage());
        if (products.size() >= 40) {
            TaobaoGoodListVO goodList = new TaobaoGoodListVO();
            Items items = new Items();
            items.setItem(products);
            goodList.setItems(items);
            return goodList;
        }

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
            List<String> translatorList = new ArrayList<>();
            for (Product item : itemList) {
                translatorList.add(item.getSellerNick());
                translatorList.add(item.getTitle());
            }
            translator.translator(translatorList, TranslatorType.ZH2RU);
            int index = 0;
            for (Product item : itemList) {
                item.setSellerNick(translatorList.get(index++));
                item.setTitle(translatorList.get(index++));
            }
            for (Product item : itemList) {
                new Thread(() -> {
                    Timestamp time = Timestamp.valueOf(TimeUtil.getLocalDateTime());
                    obApiTaobaoProductMapperHelper.insertOrUpdate(item, dto.getQ(), time);
                }).start();
            }

            List<Product> items = goodList.getItems().getItem();
            items.addAll(products);

            return goodList;
        } catch (IOException e) {
            throw new OneBoundApiException(MessageConstant.OneBoundApi_SEARCH_ERROR);
        }
    }

    @Override
    public TaobaoGoodDetailRawJsonVO taoBaoSearchDetail(TaobaoSearchDetailDTO dto) throws OneBoundApiException {
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

            return TaobaoGoodDetailRawJsonVO.builder()
                    .detailJson(body)
                    .build();
        } catch (IOException e) {
            throw new OneBoundApiException(MessageConstant.OneBoundApi_SEARCH_ERROR);
        }
    }

    @Override
    public TaobaoGoodDetailVO parseToDetail(String detailJson) {
        try {
            TaobaoGoodDetailVO taobaoGoodDetail = jsonMapper.readValue(detailJson, TaobaoGoodDetailVO.class);
            return taobaoGoodDetail;
        } catch (Exception e) {
            throw new OneBoundApiException(MessageConstant.FAIL);
        }
    }

    @Override
    public TaobaoGoodDetailVO translatorDetail(TaobaoGoodDetailVO vo) {
        Map<String, TranslatorDict> tranDict = new HashMap<>();
        List<String> tranList = new ArrayList<>();

        TaobaoGoodDetail item = vo.getItem();

        tranList.add(item.getTitle());

        List<Prop> props = item.getProps();
        for (Prop prop : props) {
            tranList.add(prop.getName());
            tranList.add(prop.getValue());
        }

        Map<String, String> propsList = null;
        if (item.getPropsList() instanceof Map) {
            propsList = (Map<String, String>) item.getPropsList();
            Set<Map.Entry<String, String>> entrySet = propsList.entrySet();
            for (Map.Entry<String, String> me : entrySet) {
                tranDict.put(me.getValue(), TranslatorDict.builder()
                        .zh(me.getValue())
                        .build());
            }
        }

        List<Sku> skuList = item.getSkus().getSku();
        for (Sku sku : skuList) {
            tranDict = skuPropService.extractSkuToTranDict(sku, tranDict);
        }

        tranList = translator.translator(tranList, TranslatorType.ZH2RU);
        tranDict = translator.translatorCache(tranDict, TranslatorType.ZH2RU);

        int index = 0;
        item.setTitle(tranList.get(index++));
        for (Prop prop : props) {
            prop.setName(tranList.get(index++));
            prop.setValue(tranList.get(index++));
        }
        
        return vo;
    }

}
