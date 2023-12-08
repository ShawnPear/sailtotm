package com.mapper.mapper_helper;

import com.entity.TaobaoGoodList.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.OneBoundApiTaobaoProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

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

    public List<Product> selectByQ(String q, String page) {
        q = '%' + q + '%';
        PageHelper.startPage(Integer.parseInt(page), 48);
        Page<Product> products = oneBoundApiTaobaoProductMapper.selectByQ(q);
        return products;
    }
}
