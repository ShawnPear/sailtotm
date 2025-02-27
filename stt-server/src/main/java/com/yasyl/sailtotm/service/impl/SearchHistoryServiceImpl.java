package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.entity.SearchItem;
import com.yasyl.sailtotm.enumeration.TranslatorType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yasyl.sailtotm.mapper.SearchItemMapper;
import com.yasyl.sailtotm.service.SearchHistoryService;
import com.yasyl.sailtotm.service.TranslatorService;
import com.yasyl.sailtotm.vo.ProductSimpleDetailVO;
import com.yasyl.sailtotm.vo.SearchHIstory.SearchHistoryListPageVO;
import com.yasyl.sailtotm.vo.SearchHIstory.SearchItemVO;
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

    @Autowired
    TranslatorService translator;

    @Override
    public SearchHistoryListPageVO getSearchHistory(String userId, String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<SearchItem> searchItems = searchItemMapper.selectPage(userId);
        List<SearchItemVO> result = new ArrayList<>();
        List<String> translatorList = new ArrayList<>();
        for (SearchItem item : searchItems.getResult()) {
            translatorList.add(item.getSearch());
        }
        translatorList = translator.translator(translatorList, TranslatorType.ZH2RU);
        int index = 0;
        for (SearchItem item : searchItems.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            result.add(SearchItemVO.builder()
                    .updatedDate(item.getUpdatedDate())
                    .q(translatorList.get(index++))
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
