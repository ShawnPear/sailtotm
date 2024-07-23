package com.yasyl.sailtotm.domain.goods.supply.service.impl;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodQueryEnum;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.common.exception.repo.SearchException;
import com.yasyl.sailtotm.domain.goods.supply.ability.ICacheGoodsSimpleAbility;
import com.yasyl.sailtotm.domain.goods.supply.ability.INetworkGoodsSimpleAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodSimpleDO;
import com.yasyl.sailtotm.domain.goods.supply.model.response.GoodSimpleListResponse;
import com.yasyl.sailtotm.domain.goods.supply.service.IGoodsSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 22:16
 **/
@Service
public class GoodsSupplyService implements IGoodsSupplyService {

    @Autowired
    ICacheGoodsSimpleAbility cacheGoodsSimpleAbility;

    @Autowired
    INetworkGoodsSimpleAbility networkGoodsSimpleAbility;


    @Override
    public GoodSimpleListResponse batchSearchGoodSimple(String keyWords, GoodQueryEnum mode, int size,int page) {
        GoodSimpleDO detailDO;
        if (mode == GoodQueryEnum.CACHE_ONLY || mode == GoodQueryEnum.COMBINE) {
            try {
                detailDO = cacheGoodsSimpleAbility.search(keyWords);
            } catch (RedisException e) {
                detailDO = networkGoodsSimpleAbility.search(keyWords);
            }
        } else if (mode == GoodQueryEnum.NETWORK_ONLY) {
            detailDO = networkGoodsSimpleAbility.search(keyWords);
        } else {
            throw new SearchException("错误的搜索类型");
        }
        if (detailDO == null) {
            throw new SearchException("numIid错误");
        }
        ArrayList<GoodSimpleDO> items = new ArrayList<>();
        items.add(detailDO);
        GoodSimpleListResponse response = GoodSimpleListResponse.builder()
                .item(items)
                .build();
        return response;
    }

}
