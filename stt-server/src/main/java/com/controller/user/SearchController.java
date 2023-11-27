package com.controller.user;

import com.constant.MessageConstant;
import com.dto.SearchKeyWordPageDTO;
import com.dto.TaobaoSearchDTO;
import com.entity.TaobaoGood.TaobaoGoodList;
import com.exception.user.ParamMissingException;
import com.result.Result;
import com.service.OneBoundApiService;
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
    public Result<TaobaoGoodList> searchByKeyWord(SearchKeyWordPageDTO dto) {
        if(Objects.equals(dto.getPage(), "") || Objects.equals(dto.getQ(), "")){
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
        TaobaoSearchDTO taobaoSearchDTO = TaobaoSearchDTO.builder()
                .q(dto.getQ())
                .page(dto.getPage())
                .startPrice("0")
                .endPrice("0")
                .cat("0")
                .build();
        TaobaoGoodList goodList = oneBoundApiService.taoBaoSearch(taobaoSearchDTO);
        return Result.success(goodList, MessageConstant.USER_SEARCH_SUCCESS);
    }
}
