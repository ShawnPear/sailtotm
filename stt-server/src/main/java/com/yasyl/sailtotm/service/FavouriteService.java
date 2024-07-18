package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.Favourite.FavouriteDelDTO;
import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.yasyl.sailtotm.vo.Favourite.FavouriteListPageVO;

public interface FavouriteService {

    public FavouriteListPageVO getFavorite(String userId, String page, String pageSize);

    public FavouriteListPageVO getFavoriteBySearch(String userId, String page, String pageSize, String q);

    public Boolean addFavorite(Product product, String userId);

    public Boolean delFavourite(FavouriteDelDTO dto);

    Boolean isFavourite(String userId, String numIid);
}
