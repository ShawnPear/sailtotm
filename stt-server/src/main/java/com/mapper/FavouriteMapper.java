package com.mapper;

import com.entity.FavouriteItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface FavouriteMapper {
    public Integer insert(String num_iid, LocalDateTime created_time, String user_id);

    public Page<FavouriteItem> selectAllByUserIdPage(String user_id);

    public Page<FavouriteItem> selectAllByUserSearchPage(String user_id, String q);

    @Delete("delete from SailToTMDB.FavouriteItems where user_id = #{userId} and favourite_id = #{favouriteId}")
    public Integer delete(String userId, Integer favouriteId);
}
