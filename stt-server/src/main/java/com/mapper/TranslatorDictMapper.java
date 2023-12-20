package com.mapper;

import com.entity.TranslatorDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TranslatorDictMapper {
    public Boolean insert(TranslatorDict dict);

    @Select("select * from sailtotmdb.TranslatorDict where translator_id = #{id} limit 1")
    public TranslatorDict selectById(Integer id);

    @Select("select * from sailtotmdb.TranslatorDict where zh = #{zh} limit 1")
    public TranslatorDict selectByZh(String zh);

    @Select("select * from sailtotmdb.TranslatorDict where ru = #{ru} limit 1")
    public TranslatorDict selectByRu(String ru);
}
