package com.yasyl.sailtotm.mapper.mapper_helper;

import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.yasyl.sailtotm.es.OneBoundApiTaobaoProductESV1;
import com.yasyl.sailtotm.mapper.OneBoundApiTaobaoProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class OneBoundApiTaobaoProductMapperHelper {

    @Autowired
    OneBoundApiTaobaoProductMapper oneBoundApiTaobaoProductMapper;


    @Autowired
    OneBoundApiTaobaoProductESV1 productES;

    public boolean insertOrUpdate(Product product, String q, Timestamp created_date) {
        boolean status;
        if (oneBoundApiTaobaoProductMapper.selectById(product.getNumIid()) == null) {
            status = oneBoundApiTaobaoProductMapper.insert(product, q, created_date) != 0;
            status &= productES.insert(product, q, created_date);
        } else {
            status = oneBoundApiTaobaoProductMapper.update(product, q, created_date) != 0;
            status &= productES.update(product, q, created_date);
        }
        return status;
    }

    public List<Product> selectByQ(String q, String page) {
        List<Product> list = productES.selectByQ(q, Integer.valueOf(page), 48);
        return list;
    }
}
