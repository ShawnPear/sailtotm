package com.mapper;

import com.entity.TaobaoGoodList.Product;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface OneBoundApiTaobaoProductMapper {
    Product selectById(String numIid);
    Integer insert(Product item, String q, Timestamp created_date);
    Integer update(Product item, String q, Timestamp created_date);
}
