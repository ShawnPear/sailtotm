package com.yasyl.sailtotm.app.controllerimpl;

import com.yasyl.sailtotm.client.controller.ISearchController;
import com.yasyl.sailtotm.client.dto.common.ItemsDTO;
import com.yasyl.sailtotm.client.dto.common.ProductDTO;
import com.yasyl.sailtotm.client.dto.common.TaobaoSearchDTO;
import com.yasyl.sailtotm.client.dto.request.SearchGoodsKeyWordPageDTO;
import com.yasyl.sailtotm.client.dto.response.TaobaoGoodDetailVO;
import com.yasyl.sailtotm.client.dto.response.TaobaoGoodListVO;
import com.yasyl.sailtotm.client.dto.result.Result;
import com.yasyl.sailtotm.common.constant.MessageConstant;
import com.yasyl.sailtotm.common.enumeration.TranslatorType;
import com.yasyl.sailtotm.common.exception.user.OneBoundApiException;
import com.yasyl.sailtotm.common.exception.user.ParamMissingException;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodQueryEnum;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;
import com.yasyl.sailtotm.domain.goods.supply.model.response.GoodDetailResponse;
import com.yasyl.sailtotm.domain.goods.supply.model.response.GoodSimpleListResponse;
import com.yasyl.sailtotm.domain.goods.supply.service.IGoodsDetailSupplyService;
import com.yasyl.sailtotm.domain.goods.supply.service.IGoodsSupplyService;
import com.yasyl.sailtotm.domain.translation.service.TranslatorService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Api(tags = "搜索操作接口")
@Slf4j
public class SearchController implements ISearchController {
    @Autowired
    TranslatorService translator;

    @Autowired
    IGoodsSupplyService goodsSupplyService;

    @Autowired
    IGoodsDetailSupplyService goodsDetailSupplyService;

    private static void buildTaobaoGoodLisVO(GoodSimpleListResponse goodSimpleListResponse, TaobaoGoodListVO goodList) {
        ItemsDTO items = new ItemsDTO();
        BeanUtils.copyProperties(items, goodSimpleListResponse);
        items.setItem(new ArrayList<>());
        for (GoodSimpleDO goodSimpleDO : goodSimpleListResponse.getItem()) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productDTO, goodSimpleDO);
            items.getItem().add(productDTO);
        }
        goodList.setItems(items);
    }

    @Override
    @GetMapping("/search")
    public Result<TaobaoGoodListVO> searchByKeyWord(SearchGoodsKeyWordPageDTO dto) {
        String zhQ = translator.translatorCache(dto.getQ(), TranslatorType.RU2ZH);
        if (Objects.equals(dto.getPage(), "") || Objects.equals(dto.getQ(), "")) {
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
        TaobaoSearchDTO taobaoSearchDTO = TaobaoSearchDTO.builder().q(zhQ).page(dto.getPage()).startPrice("0").endPrice("0").cat("0").build();
        TaobaoGoodListVO goodList = new TaobaoGoodListVO();
        GoodSimpleListResponse goodSimpleListResponse = goodsSupplyService.batchSearchGoodSimple(zhQ, GoodQueryEnum.COMBINE, 40, Integer.parseInt(dto.getPage()));
        buildTaobaoGoodLisVO(goodSimpleListResponse, goodList);

//        Long useId = BaseContext.getCurrentId();
//        searchHistoryService.addSearchHistory(dto.getQ(), useId);
        return Result.status(goodList != null, MessageConstant.USER_SEARCH_SUCCESS, MessageConstant.FAIL, goodList);
    }

    @Override
    public Result<TaobaoGoodDetailVO> searchDetailByNumIidV2(String product_id) {
        if (product_id.equals("")) {
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
        TaobaoGoodDetailVO raw = null;
        try {
            raw = new TaobaoGoodDetailVO();
            GoodDetailResponse goodDetailResponse = goodsDetailSupplyService.QueryGoodsDetailByProductId(product_id, GoodQueryEnum.COMBINE);
            if (goodDetailResponse == null || goodDetailResponse.getItem() == null) {
                throw new OneBoundApiException();
            }
            GoodDetailDO item = goodDetailResponse.getItem();
            BeanUtils.copyProperties(goodDetailResponse.getItem(), item);
//            raw = oneBoundApiService.translatorDetail(raw);
        } catch (OneBoundApiException e) {
            e.printStackTrace();
        }
        return Result.success(raw);
    }
}
