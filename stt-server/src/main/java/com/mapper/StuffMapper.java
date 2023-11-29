package com.mapper;

import com.entity.Stuff;
import com.entity.StuffStatusChange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StuffMapper {
    Stuff getByEmail(String email);

    Boolean insert(Stuff stuff);

    Stuff getById(String stuffId);

    Boolean updateStatusByStuffId(StuffStatusChange change);

}
