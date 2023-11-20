package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from Users where email = #{email}")
    User getByEmail(String email);
}
