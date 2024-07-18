package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.TransportType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransportTypeMapper {

    public Boolean add(TransportType transportType);

    public Boolean update(TransportType transportType);

    public Boolean remove(TransportType transportType);

    public List<TransportType> getAll();
}
