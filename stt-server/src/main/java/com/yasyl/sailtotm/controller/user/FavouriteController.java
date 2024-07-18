package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.annotation.CheckUserId;
import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.Favourite.FavouriteDTO;
import com.yasyl.sailtotm.dto.Favourite.FavouriteDelDTO;
import com.yasyl.sailtotm.entity.TaobaoGoodList.Product;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.FavouriteService;
import com.yasyl.sailtotm.vo.Favourite.FavouriteListPageVO;
import com.yasyl.sailtotm.vo.Favourite.IsFavouriteVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.yasyl.sailtotm.constant.MessageConstant.*;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.CLASS;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.STRING;

@RestController
@RequestMapping("/user/favourite")
@Api(tags = "收藏操作接口")
@Slf4j
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;

    @GetMapping("/{user_id}")
    @CheckUserId(STRING)
    public Result<FavouriteListPageVO> getFavourite(@PathVariable String user_id, String page, String page_size, String q) {
        FavouriteListPageVO result = FavouriteListPageVO.builder().build();
        if (q == null || q.isEmpty()) {
            result = favouriteService.getFavorite(user_id, page, page_size);
        } else {
            result = favouriteService.getFavoriteBySearch(user_id, page, page_size, q);
        }
        return Result.dataDetect(!result.getProduct_detail_list().isEmpty(), SUCCESS, NO_DATA, result);
    }

    @GetMapping("/is-user-favourite/{user_id}")
    @CheckUserId(STRING)
    public Result<IsFavouriteVO> isFavourite(@PathVariable String user_id, String num_iid) {
        Boolean status = favouriteService.isFavourite(user_id, num_iid);
        return Result.dataDetect(status, RIGHT, NO, IsFavouriteVO.builder().isFavourite(status).build());
    }

    @PostMapping
    @CheckUserId(CLASS)
    public Result addFavourite(@RequestBody FavouriteDTO dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto, product);
        Boolean status = favouriteService.addFavorite(product, dto.getUserId());
        return Result.status(status);
    }

    @DeleteMapping
    @CheckUserId(CLASS)
    public Result deleteFavourite(@RequestBody FavouriteDelDTO dto) {
        Boolean status = favouriteService.delFavourite(dto);
        return Result.status(status, SUCCESS, MessageConstant.WITHOUT_DATA_CANT_DELETE);
    }

}
