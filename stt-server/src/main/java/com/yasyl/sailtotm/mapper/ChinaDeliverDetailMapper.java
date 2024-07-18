package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.ChinaDeliverDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChinaDeliverDetailMapper {

    @Insert("insert into sailtotmdb.ChinaDeliverDetails (name, phone_number, address,status) " +
            "VALUES (#{name},#{phoneNumber},#{address},#{status})")
    public Boolean insert(ChinaDeliverDetail detail);

    @Select("select * from sailtotmdb.ChinaDeliverDetails")
    public List<ChinaDeliverDetail> selectAllPage();

    public Boolean updateById(ChinaDeliverDetail detail);

    @Delete("delete from sailtotmdb.ChinaDeliverDetails where deliver_id = #{deliverId}")
    public Boolean deleteById(ChinaDeliverDetail detail);
}
