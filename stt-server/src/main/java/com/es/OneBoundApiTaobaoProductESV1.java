package com.es;

import com.alibaba.fastjson.JSON;
import com.entity.OneBoundApiTaobaoProduct;
import com.entity.TaobaoGoodList.Product;
import com.exception.user.OneBoundApiException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.OneBoundApiTaobaoProductMapper;
import com.properties.EsProperties;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.constant.MessageConstant.FAIL;

@Service
public class OneBoundApiTaobaoProductESV1 implements OneBoundApiTaobaoProductES {

    @Autowired
    OneBoundApiTaobaoProductMapper db;

    @Autowired
    RestHighLevelClient client;

    @Autowired
    EsProperties esProperties;

    @Autowired
    JsonMapper jsonMapper;

    @Override
    public List<Product> selectByQ(String q, Integer page, Integer pageSize) {
        Integer from = (page - 1) * pageSize;
        SearchRequest search = new SearchRequest(esProperties.getOneBoundApiSearchDB());
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder(q)
                .field("q", 10).field("titleZh", 5);

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(multiMatchQueryBuilder).boostMode(CombineFunction.MULTIPLY);
        sourceBuilder.query(functionScoreQueryBuilder).from(from).size(pageSize);
        search.source(sourceBuilder);

        List<Product> list = new ArrayList<>();
        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            SearchResponse searchResponse = client.search(search, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            for (SearchHit hit : searchHits) {
                String json = hit.getSourceAsString();
                Product product = jsonMapper.readValue(json, Product.class);
                list.add(product);
            }
        } catch (Exception e) {
            throw new OneBoundApiException(FAIL);
        }

        return list;
    }

    @Override
    public Boolean insert(Product item, String q, Timestamp created_date) {
        OneBoundApiTaobaoProduct doc = new OneBoundApiTaobaoProduct(item, q, created_date);
        IndexRequest request = new IndexRequest(esProperties.getOneBoundApiSearchDB())
                .id(doc.getNumIid())
                .source(JSON.toJSONString(doc), XContentType.JSON);

        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean update(Product item, String q, Timestamp created_date) {
        OneBoundApiTaobaoProduct doc = new OneBoundApiTaobaoProduct(item, q, created_date);
        Map<String, Object> map = (Map<String, Object>) jsonMapper.convertValue(doc, Map.class);
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key != null && value == null) {
                iterator.remove();
            }
        }
        try {
            UpdateRequest request = new UpdateRequest(esProperties.getOneBoundApiSearchDB(), item.getNumIid());
            request.doc(map);

            client.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean syncDataFromDB() throws IOException {
        final int pageSize = 1000;
        int page = 1;
        while (true) {
            PageHelper.startPage(page, pageSize);
            Page<OneBoundApiTaobaoProduct> products = db.selectAllProduct();
            //构建语句

            List<OneBoundApiTaobaoProduct> result = products.getResult();
            if (result.isEmpty()) {
                break;
            }
            // 1.创建Request
            BulkRequest request = new BulkRequest();
            // 2.准备参数，添加多个新增的Request
            for (OneBoundApiTaobaoProduct doc : result) {
                // 2.2.创建新增文档的Request对象
                request.add(new IndexRequest(esProperties.getOneBoundApiSearchDB())
                        .id(doc.getNumIid())
                        .source(JSON.toJSONString(doc), XContentType.JSON));
            }
            // 3.发送请求
            BulkResponse bulk = client.bulk(request, RequestOptions.DEFAULT);
            page += 1;
            System.out.println(page * pageSize);
        }
        return true;
    }
}
