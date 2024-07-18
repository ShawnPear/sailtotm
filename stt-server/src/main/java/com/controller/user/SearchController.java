package com.controller.user;

import com.annotation.CheckUserId;
import com.annotation.Translator;
import com.context.BaseContext;
import com.dto.Search.SearchGoodsKeyWordPageDTO;
import com.dto.Search.TaobaoSearchDTO;
import com.dto.Search.TaobaoSearchDetailDTO;
import com.enumeration.TranType;
import com.enumeration.TranslatorType;
import com.exception.user.OneBoundApiException;
import com.exception.user.ParamMissingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mapper.TranslatorDictMapper;
import com.result.Result;
import com.service.OneBoundApiService;
import com.service.SearchHistoryService;
import com.service.TranslatorService;
import com.vo.SearchHIstory.SearchHistoryListPageVO;
import com.vo.TaobaoGood.TaobaoGoodDetailRawJsonVO;
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

    @Autowired
    TranslatorService translator;

    @Autowired
    TranslatorDictMapper translatorDictMapper;

    @GetMapping("/search")
    @Translator(type = TranType.in, tranType = TranslatorType.RU2ZH
            , inputParams = {"dto.q"})
    public Result<TaobaoGoodListVO> searchByKeyWord(SearchGoodsKeyWordPageDTO dto) {
        String zhQ = translator.translatorCache(dto.getQ(), TranslatorType.RU2ZH);
        if (Objects.equals(dto.getPage(), "") || Objects.equals(dto.getQ(), "")) {
            throw new ParamMissingException(PARAM_MISSING_ERROR);
        }
        TaobaoSearchDTO taobaoSearchDTO = TaobaoSearchDTO.builder().q(zhQ).page(dto.getPage()).startPrice("0").endPrice("0").cat("0").build();
        TaobaoGoodListVO goodList = oneBoundApiService.taoBaoSearch(taobaoSearchDTO);

        Long useId = BaseContext.getCurrentId();
        searchHistoryService.addSearchHistory(dto.getQ(), useId);
        return Result.status(goodList != null, USER_SEARCH_SUCCESS, FAIL, goodList);
    }

    @GetMapping("/search-item")
    public Result<TaobaoGoodDetailRawJsonVO> searchDetailByNumIid(String product_id) {
        if (product_id.equals("")) {
            throw new ParamMissingException(PARAM_MISSING_ERROR);
        }
        TaobaoSearchDetailDTO taobaoSearchDetailDTO = TaobaoSearchDetailDTO.builder().numIid(product_id).build();
        TaobaoGoodDetailRawJsonVO taobaoGoodDetail = oneBoundApiService.taoBaoSearchDetail(taobaoSearchDetailDTO);
        return Result.success(taobaoGoodDetail);
    }

    @GetMapping("/search-item/v2")
    public Result<TaobaoGoodDetailVO> searchDetailByNumIidV2(String product_id) {
        if (product_id.equals("")) {
            throw new ParamMissingException(PARAM_MISSING_ERROR);
        }
        TaobaoGoodDetailVO raw = null;
        try {
            System.out.println(1);
            TaobaoSearchDetailDTO taobaoSearchDetailDTO = TaobaoSearchDetailDTO.builder().numIid(product_id).build();
            System.out.println(2);
            TaobaoGoodDetailRawJsonVO taobaoGoodDetail = oneBoundApiService.taoBaoSearchDetail(taobaoSearchDetailDTO);
            System.out.println(3);
            raw = oneBoundApiService.parseToDetail(taobaoGoodDetail.getDetailJson());
            System.out.println(4);
            raw = oneBoundApiService.translatorDetail(raw);
        } catch (OneBoundApiException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return Result.success(raw);
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
