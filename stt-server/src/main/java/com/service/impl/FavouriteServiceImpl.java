package com.service.impl;

import com.dto.Favourite.FavouriteDelDTO;
import com.entity.FavouriteItem;
import com.entity.TaobaoGoodList.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.FavouriteMapper;
import com.mapper_helper.OneBoundApiTaobaoProductMapperHelper;
import com.service.FavouriteService;
import com.vo.Favourite.FavouriteItemVO;
import com.vo.Favourite.FavouriteListPageVO;
import com.vo.ProductSimpleDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    FavouriteMapper favouriteMapper;

    @Autowired
    OneBoundApiTaobaoProductMapperHelper oneBoundApiTaobaoProductMapperHelper;

    @Override
    public FavouriteListPageVO getFavorite(String userId, String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<FavouriteItem> favouriteItems = favouriteMapper.selectAllByUserIdPage(userId);
        List<FavouriteItemVO> result = new ArrayList<>();
        for (FavouriteItem item : favouriteItems.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            result.add(FavouriteItemVO.builder()
                    .favouriteId(item.getFavouriteId())
                    .createdDate(item.getCreatedDate())
                    .productDetail(psdVO)
                    .build());
        }
        return FavouriteListPageVO.builder()
                .page(page)
                .pageSize(String.valueOf(favouriteItems.getPageSize()))
                .product_detail_list(result)
                .build();
    }

    @Override
    public FavouriteListPageVO getFavoriteBySearch(String userId, String page, String pageSize, String q) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<FavouriteItem> favouriteItems = favouriteMapper.selectAllByUserSearchPage(userId, '%' + q + '%');
        List<FavouriteItemVO> result = new ArrayList<>();
        for (FavouriteItem item : favouriteItems.getResult()) {
            ProductSimpleDetailVO psdVO = ProductSimpleDetailVO.builder().build();
            BeanUtils.copyProperties(item, psdVO);
            result.add(FavouriteItemVO.builder()
                    .favouriteId(item.getFavouriteId())
                    .createdDate(item.getCreatedDate())
                    .productDetail(psdVO)
                    .build());
        }
        return FavouriteListPageVO.builder()
                .page(page)
                .pageSize(String.valueOf(favouriteItems.getPageSize()))
                .product_detail_list(result)
                .build();
    }

    @Override
    public Boolean addFavorite(Product product, String userId) {
        LocalDateTime createdTime = LocalDateTime.now();
        oneBoundApiTaobaoProductMapperHelper.insertOrUpdate(product, "", Timestamp.valueOf(createdTime));
        return favouriteMapper.insert(product.getNumIid(), createdTime, userId) > 0;
    }

    @Override
    public Boolean delFavourite(FavouriteDelDTO dto) {
        Boolean status = favouriteMapper.delete(dto.getUserId(), Integer.valueOf(dto.getFavouriteId())) != 0;
        return status;
    }
}
