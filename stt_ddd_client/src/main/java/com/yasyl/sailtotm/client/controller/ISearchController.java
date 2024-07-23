package com.yasyl.sailtotm.client.controller;

import com.yasyl.sailtotm.client.dto.request.SearchGoodsKeyWordPageDTO;
import com.yasyl.sailtotm.client.dto.response.TaobaoGoodDetailVO;
import com.yasyl.sailtotm.client.dto.response.TaobaoGoodListVO;
import com.yasyl.sailtotm.client.dto.result.Result;

/**
 * @program: SailToTm
 * @description: 搜索Controller
 * @author: wujubin
 * @create: 2024-07-23 19:58
 **/
public interface ISearchController {
    Result<TaobaoGoodListVO> searchByKeyWord(SearchGoodsKeyWordPageDTO dto);

    Result<TaobaoGoodDetailVO> searchDetailByNumIidV2(String product_id);
}
