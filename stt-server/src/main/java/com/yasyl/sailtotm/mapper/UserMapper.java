package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.User;
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

    @Update("update SAILTOTM.Users set enable = #{enable} where email = #{email}")
    Boolean updateActivateStatusByEmail(String email,int enable);
}
