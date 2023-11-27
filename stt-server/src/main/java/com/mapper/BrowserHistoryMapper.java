package com.mapper;

import com.entity.BrowserHistory;
import com.entity.TaobaoGoodList.Product;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface BrowserHistoryMapper {
    public Integer insert(Product item, LocalDateTime created_time, String user_id);

    public Page<BrowserHistory> selectAllByUserIdPage(String user_id);
}
