package com.yasyl.sailtotm.domain.goods.supply.service.impl;

import com.yasyl.sailtotm.domain.goods.supply.entity.GoodQueryEnum;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.common.exception.repo.SearchException;
import com.yasyl.sailtotm.domain.goods.supply.ability.ICacheGoodsDetailAbility;
import com.yasyl.sailtotm.domain.goods.supply.ability.INetworkGoodsDetailAbility;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;
import com.yasyl.sailtotm.domain.goods.supply.model.response.GoodDetailResponse;
import com.yasyl.sailtotm.domain.goods.supply.service.IGoodsDetailSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 20:10
 **/
@Service
public class GoodsDetailSupplyService implements IGoodsDetailSupplyService {

    @Autowired
    ICacheGoodsDetailAbility cacheGoodsDetailAbility;

    @Autowired
    INetworkGoodsDetailAbility networkGoodsDetailAbility;

    @Override
    public GoodDetailResponse QueryGoodsDetailByProductId(String numIid, GoodQueryEnum mode) {
        GoodDetailDO detailDO = getDetailDO(numIid, mode);
        GoodDetailResponse response = new GoodDetailResponse();
        response.setItem(detailDO);
        return response;
    }

    @Override
    public List<GoodDetailResponse> batchQueryGoodsDetailByProductId(List<String> numIidList, GoodQueryEnum mode) {
        List<GoodDetailResponse> responseList = new ArrayList<>();
        for (String numIid : numIidList) {
            GoodDetailDO detailDO = getDetailDO(numIid, mode);
            GoodDetailResponse response = new GoodDetailResponse();
            response.setItem(detailDO);
            responseList.add(response);
        }
        return responseList;
    }

    private GoodDetailDO getDetailDO(String numIid, GoodQueryEnum mode) {
        GoodDetailDO detailDO;
        if (mode == GoodQueryEnum.CACHE_ONLY || mode == GoodQueryEnum.COMBINE) {
            try {
                detailDO = cacheGoodsDetailAbility.query(numIid);
            } catch (RedisException e) {
                detailDO = networkGoodsDetailAbility.query(numIid);
            }
        } else if (mode == GoodQueryEnum.NETWORK_ONLY) {
            detailDO = networkGoodsDetailAbility.query(numIid);
        } else {
            throw new SearchException("错误的搜索类型");
        }
        return detailDO;
    }
}
