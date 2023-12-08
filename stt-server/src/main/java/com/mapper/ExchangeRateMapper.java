package com.mapper;

import com.entity.ExchangeRate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExchangeRateMapper {

    @Insert("insert into sailtotmdb.ExchangeRate (rmb, usd, manat, updated_date)" +
            " VALUES (#{rmb},#{usd},#{manat},#{updatedDate})")
    public Boolean insert(ExchangeRate rate);

    @Select("select * from sailtotmdb.ExchangeRate order by updated_date desc limit 1")
    public ExchangeRate selectNewest();
}
