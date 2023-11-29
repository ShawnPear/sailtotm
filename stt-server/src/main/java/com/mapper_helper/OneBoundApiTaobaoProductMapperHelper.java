package com.mapper_helper;

import com.entity.TaobaoGoodList.Product;
import com.mapper.OneBoundApiTaobaoProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class OneBoundApiTaobaoProductMapperHelper {

    @Autowired
    OneBoundApiTaobaoProductMapper oneBoundApiTaobaoProductMapper;

    public boolean insertOrUpdate(Product product, String q, Timestamp created_date) {
        boolean status;
        if (oneBoundApiTaobaoProductMapper.selectById(product.getNumIid()) == null) {
            status = oneBoundApiTaobaoProductMapper.insert(product, q, created_date) != 0;
        } else {
            status = oneBoundApiTaobaoProductMapper.update(product, q, created_date) != 0;
        }
        return status;
    }
}
