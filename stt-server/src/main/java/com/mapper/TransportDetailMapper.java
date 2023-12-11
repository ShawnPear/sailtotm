package com.mapper;

import com.entity.Order.Transport.TransportDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransportDetailMapper {

    public Boolean add(TransportDetail transportDetail);

    public Boolean modifySize(Integer id, Integer width, Integer height, Integer length, Double weight);
}
