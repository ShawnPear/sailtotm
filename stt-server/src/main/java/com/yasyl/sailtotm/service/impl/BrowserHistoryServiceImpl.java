package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.entity.BrowserHistoryItem;
import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yasyl.sailtotm.mapper.BrowserHistoryMapper;
import com.yasyl.sailtotm.mapper.mapper_helper.OneBoundApiTaobaoProductMapperHelper;
import com.yasyl.sailtotm.service.BrowserHistoryService;
import com.yasyl.sailtotm.vo.BrowserHistory.BrowserHistoryItemVO;
import com.yasyl.sailtotm.vo.BrowserHistory.BrowserHistoryListPageVO;
import com.yasyl.sailtotm.vo.ProductSimpleDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        for (BrowserHistoryItem item : browserHistories.getResult()) {
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
        Page<BrowserHistoryItem> browserHistories = browserHistoryMapper.selectAllByUserSearchPage(userId, '%'+q+'%');
        List<BrowserHistoryItemVO> result = new ArrayList<>();
        for (BrowserHistoryItem item : browserHistories.getResult()) {
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
        BrowserHistoryItem latestOne = browserHistoryMapper.getLatestOne(userId);
        if(latestOne!= null && Objects.equals(latestOne.getNumIid(), product.getNumIid())){
            LocalDateTime updatedTime = LocalDateTime.now();
            return browserHistoryMapper.updateTime(product.getNumIid(),updatedTime,userId);
        }
        LocalDateTime createdTime = LocalDateTime.now();
        oneBoundApiTaobaoProductMapperHelper.insertOrUpdate(product, "", Timestamp.valueOf(createdTime));
        return browserHistoryMapper.insert(product.getNumIid(), createdTime, userId) > 0;
    }
}
