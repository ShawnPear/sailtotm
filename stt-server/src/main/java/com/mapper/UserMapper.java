package com.mapper;

import com.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from Users where email = #{email}")
    User getByEmail(String email);

    Boolean insert(User user);

    @Select("select * from Users where user_id = #{id}")
    User getByUserId(Long id);

    Boolean updateByUserId(User user);
}
