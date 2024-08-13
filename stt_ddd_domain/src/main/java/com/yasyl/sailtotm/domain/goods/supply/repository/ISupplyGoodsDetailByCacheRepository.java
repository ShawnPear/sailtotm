package com.yasyl.sailtotm.domain.goods.supply.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.domain.goods.supply.entity.GoodDetailDO;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-08-07 14:29
 **/
public interface ISupplyGoodsDetailByCacheRepository {
    void save(String numIid, GoodDetailDO goodDetailDO);

    GoodDetailDO query(String numIid) throws RedisException, JsonProcessingException;
}
