package com.mapper;

import com.entity.InterestRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InterestRateMapper {

    @Update("update SailToTMDB.InterestRate set interest_rate = #{interestRate}")
    public Boolean update(InterestRate rate);

    @Select("select * from SailToTMDB.InterestRate limit 1")
    public InterestRate selectNewest();
}
