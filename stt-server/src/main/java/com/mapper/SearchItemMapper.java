package com.mapper;

import com.entity.SearchItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SearchItemMapper {

    @Insert("insert into SailToTMDB.SearchItems (user_id, search, searched_times, created_date,updated_date) VALUES " +
            "(#{userId},#{search},#{searchedTimes},#{createdDate},#{updatedDate})")
    Boolean add(SearchItem item);

    @Update("update SailToTMDB.SearchItems " +
            "set searched_times = #{searchedTimes} ,updated_date = #{updatedDate} " +
            "where user_id = #{userId} and search = #{search}")
    Boolean updateSearchedTimes(SearchItem item);

    @Select("select * from SailToTMDB.SearchItems where user_id = #{userId} and search = #{search}")
    SearchItem selectByQandUserId(String search, String userId);

    @Select("select * from SailToTMDB.SearchItems where user_id = #{userId} order by updated_date desc")
    Page<SearchItem> selectPage(String userId);
}
