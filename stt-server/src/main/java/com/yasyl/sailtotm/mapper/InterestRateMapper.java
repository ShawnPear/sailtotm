package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.InterestRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InterestRateMapper {

    @Update("update sailtotmdb.InterestRate set interest_rate = #{interestRate}")
    public Boolean update(InterestRate rate);

    @Select("select * from sailtotmdb.InterestRate limit 1")
    public InterestRate selectNewest();
}
