package com.yasyl.sailtotm.infra.dal.dao;

import com.yasyl.sailtotm.infra.dal.entity.UserGoodsSourcePreferencePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-20 22:44
 **/
@Mapper
public interface UserGoodsSourcePrefernceDao {
    public Boolean insert(UserGoodsSourcePreferencePO po);
    public Boolean update(UserGoodsSourcePreferencePO po);
    public UserGoodsSourcePreferencePO queryByUserId(long userId);
}
