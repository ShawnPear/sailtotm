package com.controller.user;

import com.constant.MessageConstant;
import com.dto.SearchGoodsKeyWordPageDTO;
import com.dto.TaobaoSearchDTO;
import com.dto.TaobaoSearchDetailDTO;
import com.exception.user.ParamMissingException;
import com.result.PageResult;
import com.result.Result;
import com.service.OneBoundApiService;
import com.vo.TaobaoGoodDetailVO;
import com.vo.TaobaoGoodListVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
@Api(tags = "搜索操作接口")
@Slf4j
public class SearchController {

    @Autowired
    OneBoundApiService oneBoundApiService;

    @GetMapping("/search")
    public Result<TaobaoGoodListVO> searchByKeyWord(SearchGoodsKeyWordPageDTO dto) {
        if (Objects.equals(dto.getPage(), "") || Objects.equals(dto.getQ(), "")) {
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
        TaobaoSearchDTO taobaoSearchDTO = TaobaoSearchDTO.builder()
                .q(dto.getQ())
                .page(dto.getPage())
                .startPrice("0")
                .endPrice("0")
                .cat("0")
                .build();
        TaobaoGoodListVO goodList = oneBoundApiService.taoBaoSearch(taobaoSearchDTO);
        return Result.success(goodList, MessageConstant.USER_SEARCH_SUCCESS);
    }

    @GetMapping("/search_page")
    public Result<PageResult> searchByKeyWord2(SearchGoodsKeyWordPageDTO dto) {
        if (Objects.equals(dto.getPage(), "") || Objects.equals(dto.getQ(), "")) {
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
        TaobaoSearchDTO taobaoSearchDTO = TaobaoSearchDTO.builder()
                .q(dto.getQ())
                .page(dto.getPage())
                .startPrice("0")
                .endPrice("0")
                .cat("0")
                .build();
        TaobaoGoodListVO goodList = oneBoundApiService.taoBaoSearch(taobaoSearchDTO);
        PageResult pageResult = PageResult.builder()
                .total(48)
                .records(goodList.getItems().getItem())
                .build();
        return Result.success(pageResult, MessageConstant.USER_SEARCH_SUCCESS);
    }

    @GetMapping("/search-item")
    public Result<TaobaoGoodDetailVO> searchDetailByNumIid(String product_id) {
        if (product_id.equals("")) {
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
        TaobaoSearchDetailDTO taobaoSearchDetailDTO = TaobaoSearchDetailDTO.builder()
                .numIid(product_id)
                .build();
        TaobaoGoodDetailVO taobaoGoodDetail = oneBoundApiService.taoBaoSearchDetail(taobaoSearchDetailDTO);
        return Result.success(taobaoGoodDetail);
    }
}
