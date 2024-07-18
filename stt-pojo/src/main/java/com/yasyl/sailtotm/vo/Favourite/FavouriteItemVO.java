package com.yasyl.sailtotm.vo.Favourite;

import com.yasyl.sailtotm.vo.ProductSimpleDetailVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteItemVO {
    private String createdDate;
    private ProductSimpleDetailVO productDetail;
    private int favouriteId;
}
