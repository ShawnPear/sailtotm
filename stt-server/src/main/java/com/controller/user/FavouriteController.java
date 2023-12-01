package com.controller.user;

import com.constant.MessageConstant;
import com.dto.Favourite.FavouriteDTO;
import com.dto.Favourite.FavouriteDelDTO;
import com.entity.TaobaoGoodList.Product;
import com.result.Result;
import com.service.FavouriteService;
import com.vo.Favourite.FavouriteListPageVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/favourite")
@Api(tags = "收藏操作接口")
@Slf4j
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;

    @GetMapping("/{user_id}")
    public Result<FavouriteListPageVO> getFavourite(@PathVariable String user_id, String page, String page_size, String q) {
        FavouriteListPageVO result = FavouriteListPageVO.builder().build();
        if (q == null || q.isEmpty()) {
            result = favouriteService.getFavorite(user_id, page, page_size);
        } else {
            result = favouriteService.getFavoriteBySearch(user_id, page, page_size, q);
        }
        if (!result.getProduct_detail_list().isEmpty()) {
            return Result.success(result, MessageConstant.SUCCESS);
        }else{
            return Result.success(result, MessageConstant.NO_DATA);
        }
    }

    @PostMapping
    public Result addFavourite(@RequestBody FavouriteDTO dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        Boolean status = favouriteService.addFavorite(product, dto.getUserId());
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.error(MessageConstant.FAIL);
        }
    }

    @DeleteMapping
    public Result deleteFavourite(@RequestBody FavouriteDelDTO dto) {
        Boolean status = favouriteService.delFavourite(dto);
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.error(MessageConstant.WITHOUT_DATA_CANT_DELETE);
        }
    }

}
