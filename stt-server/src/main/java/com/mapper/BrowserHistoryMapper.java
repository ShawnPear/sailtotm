package com.mapper;

import com.entity.BrowserHistoryItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface BrowserHistoryMapper {
    public Integer insert(String num_iid, LocalDateTime created_time, String user_id);

    public Page<BrowserHistoryItem> selectAllByUserIdPage(String user_id);

    public Page<BrowserHistoryItem> selectAllByUserSearchPage(String user_id, String q);

    public BrowserHistoryItem getLatestOne(String user_id);

    public Boolean updateTime(String numIid, LocalDateTime updatedTime, String userId);
}
