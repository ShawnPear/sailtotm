package com.yasyl.sailtotm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PickUpBaseMapper {

    @Select("SELECT pick_up_code FROM sailtotmdb.PickUpBase WHERE enable = true ORDER BY RAND() LIMIT 1")
    public Integer getPickUpCode();

    @Update("update sailtotmdb.PickUpBase set enable = #{enable} where pick_up_code = #{code}")
    public Boolean changeEnable(Integer code, Boolean enable);

    @Insert("insert into sailtotmdb.PickUpBase (pick_up_code) VALUES (#{code})")
    public Boolean addNewPickUpCode(Integer code);
}
