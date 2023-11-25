package com.mapper;

import com.entity.Stuff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StuffMapper {
    @Select("select * from Stuff where email = #{email}")
    Stuff getByEmail(String email);

    Boolean insert(Stuff stuff);

}
