package com.yasyl.sailtotm.domain.goods.supply.service;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodQueryEnum;
import com.yasyl.sailtotm.domain.goods.supply.model.response.GoodSimpleListResponse;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:08
 **/
public interface IGoodsSupplyService {

    GoodSimpleListResponse batchSearchGoodSimple(String keyWords, GoodQueryEnum mode, int size, int page);
}
