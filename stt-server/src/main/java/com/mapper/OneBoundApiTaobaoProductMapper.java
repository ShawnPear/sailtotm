package com.mapper;

import com.entity.TaobaoGoodList.Product;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface OneBoundApiTaobaoProductMapper {

    Integer insertOrReplace(Product item, String q, LocalDateTime created_date);
}
