package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.Order.Good.GoodDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodDetailMapper {

    public Boolean insertGoodDetail(GoodDetail goodDetail);
}
