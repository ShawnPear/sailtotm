package com.yasyl.sailtotm.vo.Favourite;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FavouriteListPageVO {
    String page;
    String pageSize;
    List<FavouriteItemVO> product_detail_list;
}
