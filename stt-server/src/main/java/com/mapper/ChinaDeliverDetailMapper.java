package com.mapper;

import com.entity.ChinaDeliverDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChinaDeliverDetailMapper {

    @Insert("insert into SailToTMDB.ChinaDeliverDetails (name, phone_number, address,status) " +
            "VALUES (#{name},#{phoneNumber},#{address},#{status})")
    public Boolean insert(ChinaDeliverDetail detail);

    @Select("select * from SailToTMDB.ChinaDeliverDetails")
    public List<ChinaDeliverDetail> selectAllPage();

    public Boolean updateById(ChinaDeliverDetail detail);

    @Delete("delete from SailToTMDB.ChinaDeliverDetails where deliver_id = #{deliverId}")
    public Boolean deleteById(ChinaDeliverDetail detail);
}
