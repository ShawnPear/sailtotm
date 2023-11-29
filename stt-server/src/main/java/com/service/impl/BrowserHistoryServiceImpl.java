package com.service.impl;

import com.entity.BrowserHistoryItem;
import com.entity.TaobaoGoodList.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.BrowserHistoryMapper;
import com.mapper_helper.OneBoundApiTaobaoProductMapperHelper;
import com.service.BrowserHistoryService;
import com.vo.BrowserHistory.BrowserHistoryItemVO;
import com.vo.BrowserHistory.BrowserHistoryListPageVO;
import com.vo.ProductSimpleDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrowserHistoryServiceImpl implements BrowserHistoryService {
    @Autowired
    BrowserHistoryMapper browserHistoryMapper;

    @Autowired
    OneBoundApiTaobaoProductMapperHelper oneBoundApiTaobaoProductMapperHelper;

    @Override
    public BrowserHistoryListPageVO getBrowserHistory(String userId, String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<BrowserHistoryItem> browserHistories = browserHistoryMapper.selectAllByUserIdPage(userId);
        List<BrowserHistoryItemVO> result = new ArrayList<>();
        for (com.entity.BrowserHistoryItem item : browserHistories.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            result.add(BrowserHistoryItemVO.builder()
                    .createdDate(item.getCreatedDate())
                    .productDetail(psdVO)
                    .build());
        }
        return BrowserHistoryListPageVO.builder()
                .page(page)
                .pageSize(String.valueOf(browserHistories.getPageSize()))
                .product_detail_list(result)
                .build();
    }

    @Override
    public BrowserHistoryListPageVO getBrowserHistoryBySearch(String userId, String page, String pageSize, String q) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<com.entity.BrowserHistoryItem> browserHistories = browserHistoryMapper.selectAllByUserSearchPage(userId, '%'+q+'%');
        List<BrowserHistoryItemVO> result = new ArrayList<>();
        for (com.entity.BrowserHistoryItem item : browserHistories.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            result.add(BrowserHistoryItemVO.builder()
                    .createdDate(item.getCreatedDate())
                    .productDetail(psdVO)
                    .build());
        }
        return BrowserHistoryListPageVO.builder()
                .page(page)
                .pageSize(String.valueOf(browserHistories.getPageSize()))
                .product_detail_list(result)
                .build();
    }

    @Override
    public Boolean addBrowserHistory(Product product, String userId) {
        LocalDateTime createdTime = LocalDateTime.now();
        oneBoundApiTaobaoProductMapperHelper.insertOrUpdate(product, "", Timestamp.valueOf(createdTime));
        return browserHistoryMapper.insert(product.getNumIid(), createdTime, userId) > 0;
    }
}
