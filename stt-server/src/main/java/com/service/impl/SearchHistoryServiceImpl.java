package com.service.impl;

import com.entity.SearchItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.SearchItemMapper;
import com.service.SearchHistoryService;
import com.vo.ProductSimpleDetailVO;
import com.vo.SearchHIstory.SearchHistoryListPageVO;
import com.vo.SearchHIstory.SearchItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    SearchItemMapper searchItemMapper;

    @Override
    public SearchHistoryListPageVO getSearchHistory(String userId, String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<SearchItem> searchItems = searchItemMapper.selectPage(userId);
        List<SearchItemVO> result = new ArrayList<>();
        for (SearchItem item : searchItems.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            result.add(SearchItemVO.builder()
                    .updatedDate(item.getUpdatedDate())
                    .q(item.getSearch())
                    .build());
        }
        return SearchHistoryListPageVO.builder()
                .page(page)
                .pageSize(String.valueOf(searchItems.getPageSize()))
                .searchList(result)
                .build();
    }

    @Override
    public Boolean addSearchHistory(String q, Long userId) {
        SearchItem searchItem = searchItemMapper.selectByQandUserId(q, userId.toString());
        Boolean status;
        if (searchItem == null) {
            String now = LocalDateTime.now().toString();
            SearchItem item = SearchItem.builder()
                    .search(q)
                    .searchedTimes(1)
                    .createdDate(now)
                    .updatedDate(now)
                    .userId(String.valueOf(userId))
                    .build();
            status = searchItemMapper.add(item);
        } else {
            searchItem.setSearchedTimes(searchItem.getSearchedTimes() + 1);
            searchItem.setUpdatedDate(LocalDateTime.now().toString());
            status = searchItemMapper.updateSearchedTimes(searchItem);
        }
        return status;
    }
}
