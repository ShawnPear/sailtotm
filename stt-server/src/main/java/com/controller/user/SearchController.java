package com.controller.user;

import com.annotation.CheckUserId;
import com.context.BaseContext;
import com.dto.Search.SearchGoodsKeyWordPageDTO;
import com.dto.Search.TaobaoSearchDTO;
import com.dto.Search.TaobaoSearchDetailDTO;
import com.exception.user.ParamMissingException;
import com.result.Result;
import com.service.OneBoundApiService;
import com.service.SearchHistoryService;
import com.vo.SearchHIstory.SearchHistoryListPageVO;
import com.vo.TaobaoGood.TaobaoGoodDetailVO;
import com.vo.TaobaoGood.TaobaoGoodListVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.constant.MessageConstant.*;
import static com.enumeration.UserIdIntoType.CLASS;
import static com.enumeration.UserIdIntoType.STRING;

@RestController
@RequestMapping("/user")
@Api(tags = "搜索操作接口")
@Slf4j
public class SearchController {

    @Autowired
    OneBoundApiService oneBoundApiService;

    @Autowired
    SearchHistoryService searchHistoryService;

    @GetMapping("/search")
    @CheckUserId(CLASS)
    public Result<TaobaoGoodListVO> searchByKeyWord(SearchGoodsKeyWordPageDTO dto) {
        if (Objects.equals(dto.getPage(), "") || Objects.equals(dto.getQ(), "")) {
            throw new ParamMissingException(PARAM_MISSING_ERROR);
        }
        TaobaoSearchDTO taobaoSearchDTO = TaobaoSearchDTO.builder().q(dto.getQ()).page(dto.getPage()).startPrice("0").endPrice("0").cat("0").build();
        TaobaoGoodListVO goodList = oneBoundApiService.taoBaoSearch(taobaoSearchDTO);

//        增加搜索历史
        Boolean status = searchHistoryService.addSearchHistory(dto.getQ(), BaseContext.getCurrentId());
        return Result.status(status, USER_SEARCH_SUCCESS, FAIL, goodList);
    }

    @GetMapping("/search-item")
    public Result<TaobaoGoodDetailVO> searchDetailByNumIid(String product_id) {
        if (product_id.equals("")) {
            throw new ParamMissingException(PARAM_MISSING_ERROR);
        }
        TaobaoSearchDetailDTO taobaoSearchDetailDTO = TaobaoSearchDetailDTO.builder().numIid(product_id).build();
        TaobaoGoodDetailVO taobaoGoodDetail = oneBoundApiService.taoBaoSearchDetail(taobaoSearchDetailDTO);
        return Result.success(taobaoGoodDetail);
    }

    @GetMapping("/search-history/{user_id}")
    @CheckUserId(STRING)
    public Result<SearchHistoryListPageVO> getSearchHistoryPage(@PathVariable String user_id, String page, String page_size) {
        SearchHistoryListPageVO result;
        result = searchHistoryService.getSearchHistory(user_id, page, page_size);
        return Result.dataDetect(!result.getSearchList().isEmpty(),
                SUCCESS, NO_DATA, result);
    }
}
