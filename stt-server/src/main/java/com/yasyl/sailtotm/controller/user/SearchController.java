package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.annotation.CheckUserId;
import com.yasyl.sailtotm.annotation.Translator;
import com.yasyl.sailtotm.context.BaseContext;
import com.yasyl.sailtotm.dto.Search.SearchGoodsKeyWordPageDTO;
import com.yasyl.sailtotm.dto.Search.TaobaoSearchDTO;
import com.yasyl.sailtotm.dto.Search.TaobaoSearchDetailDTO;
import com.yasyl.sailtotm.enumeration.TranType;
import com.yasyl.sailtotm.enumeration.TranslatorType;
import com.yasyl.sailtotm.exception.user.OneBoundApiException;
import com.yasyl.sailtotm.exception.user.ParamMissingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yasyl.sailtotm.mapper.TranslatorDictMapper;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.OneBoundApiService;
import com.yasyl.sailtotm.service.SearchHistoryService;
import com.yasyl.sailtotm.service.TranslatorService;
import com.yasyl.sailtotm.vo.SearchHIstory.SearchHistoryListPageVO;
import com.yasyl.sailtotm.vo.TaobaoGood.TaobaoGoodDetailRawJsonVO;
import com.yasyl.sailtotm.vo.TaobaoGood.TaobaoGoodDetailVO;
import com.yasyl.sailtotm.vo.TaobaoGood.TaobaoGoodListVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.yasyl.sailtotm.constant.MessageConstant.*;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.STRING;

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
            TaobaoSearchDetailDTO taobaoSearchDetailDTO = TaobaoSearchDetailDTO.builder().numIid(product_id).build();
            TaobaoGoodDetailRawJsonVO taobaoGoodDetail = oneBoundApiService.taoBaoSearchDetail(taobaoSearchDetailDTO);
            raw = oneBoundApiService.parseToDetail(taobaoGoodDetail.getDetailJson());
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
