package com.mapper;

import com.entity.Order.Good.GoodDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodDetailMapper {

    public Boolean insertGoodDetail(GoodDetail goodDetail);
}
