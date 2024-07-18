package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.Stuff;
import com.yasyl.sailtotm.entity.StuffStatusChange;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StuffMapper {
    Stuff getByEmail(String email);

    Boolean insert(Stuff stuff);

    Stuff getById(String stuffId);

    Boolean updateStatusByStuffId(StuffStatusChange change);

}
