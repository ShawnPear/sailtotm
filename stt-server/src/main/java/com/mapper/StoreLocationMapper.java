package com.mapper;

import com.entity.StoreLocation;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StoreLocationMapper {

    @Insert("insert into SailToTMDB.StoreLocations (location_id, location) " +
            "VALUES (#{locationId},#{location})")
    public Boolean add(StoreLocation storeLocation);

    @Update("update SailToTMDB.StoreLocations set location = #{location} where location_id = #{locationId}")
    public Boolean update(StoreLocation storeLocation);

    @Select("select * from SailToTMDB.StoreLocations")
    public List<StoreLocation> getAll();

    @Select("select * from SailToTMDB.StoreLocations where location_id = #{id}")
    StoreLocation getAllById(String id);

    @Select("select * from SailToTMDB.StoreLocations order by location_id")
    Page<StoreLocation> getAllPage();
}
