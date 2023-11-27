package com.mapper;

import com.entity.TaobaoGood.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface OneBoundApiTaobaoProductMapper{

//    @Insert("insert into ")
    Integer insert(Item item, String q, LocalDateTime created_date);
}
