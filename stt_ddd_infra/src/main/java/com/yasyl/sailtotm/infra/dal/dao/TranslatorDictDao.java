package com.yasyl.sailtotm.infra.dal.dao;

import com.yasyl.sailtotm.infra.dal.entity.TranslatorDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TranslatorDictDao {
    public Boolean insert(TranslatorDict dict);

    @Select("select * from SAILTOTM.TranslatorDict where translator_id = #{id} limit 1")
    public TranslatorDict selectById(Integer id);

    @Select("select * from SAILTOTM.TranslatorDict where zh = #{zh} limit 1")
    public TranslatorDict selectByZh(String zh);

    @Select("select * from SAILTOTM.TranslatorDict where ru = #{ru} limit 1")
    public TranslatorDict selectByRu(String ru);
}
