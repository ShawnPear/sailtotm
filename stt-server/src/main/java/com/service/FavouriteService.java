package com.service;

import com.dto.Favourite.FavouriteDelDTO;
import com.entity.TaobaoGoodList.Product;
import com.vo.Favourite.FavouriteListPageVO;

public interface FavouriteService {

    public FavouriteListPageVO getFavorite(String userId, String page, String pageSize);

    public FavouriteListPageVO getFavoriteBySearch(String userId, String page, String pageSize, String q);

    public Boolean addFavorite(Product product, String userId);

    public Boolean delFavourite(FavouriteDelDTO dto);
}
