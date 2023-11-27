package com.mapper;

import com.entity.TaobaoGoodList.Item;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface OneBoundApiTaobaoProductMapper {

    Integer insertOrReplace(Item item, String q, LocalDateTime created_date);
}
