package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.OneBoundApiTaobaoProduct;
import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface OneBoundApiTaobaoProductMapper {
    Product selectById(String numIid);
    Page<Product> selectByQ(String q);
    Integer insert(Product item, String q, Timestamp created_date);
    Integer update(Product item, String q, Timestamp created_date);
    Page<OneBoundApiTaobaoProduct> selectAllProduct();
}
