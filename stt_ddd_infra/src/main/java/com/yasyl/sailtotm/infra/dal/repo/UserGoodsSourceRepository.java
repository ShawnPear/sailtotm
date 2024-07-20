package com.yasyl.sailtotm.infra.dal.repo;

import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import com.yasyl.sailtotm.domain.userpreference.repository.dal.IUserGoodsSourceRepository;
import com.yasyl.sailtotm.infra.dal.converter.UserGoodsSourceDalConverter;
import com.yasyl.sailtotm.infra.dal.dao.UserGoodsSourcePrefernceMapper;
import com.yasyl.sailtotm.infra.dal.entity.UserGoodsSourcePreferencePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-20 10:09
 **/
@Service
public class UserGoodsSourceRepository implements IUserGoodsSourceRepository {
    @Autowired
    private UserGoodsSourcePrefernceMapper mapper;

    @Override
    public GoodSourceStaticDO queryUserGoodsSource(long userId) {
        UserGoodsSourcePreferencePO userGoodsSourcePreferencePO = mapper.queryByUserId(userId);
        if (userGoodsSourcePreferencePO == null) {
            return null;
        }
        return UserGoodsSourceDalConverter.convert2UserGoodsSourcePreferenceDO(userGoodsSourcePreferencePO);
    }

    @Override
    public boolean updateUserGoodsSource(GoodSourceStaticDO goodSourceStaticDO) {
        return mapper.update(UserGoodsSourceDalConverter.convert2UserGoodsSourcePreferencePO(goodSourceStaticDO));
    }

    @Override
    public boolean insertUserGoodsSource(GoodSourceStaticDO goodSourceStaticDO) {
        return mapper.insert(UserGoodsSourceDalConverter.convert2UserGoodsSourcePreferencePO(goodSourceStaticDO));
        ;
    }
}
