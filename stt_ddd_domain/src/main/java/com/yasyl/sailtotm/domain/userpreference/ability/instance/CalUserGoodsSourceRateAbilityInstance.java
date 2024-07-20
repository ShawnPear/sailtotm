package com.yasyl.sailtotm.domain.userpreference.ability.instance;

import com.yasyl.sailtotm.common.constant.MessageConstant;
import com.yasyl.sailtotm.common.exception.user.ParamMissingException;
import com.yasyl.sailtotm.domain.payment.entity.UserDO;
import com.yasyl.sailtotm.domain.userpreference.ability.ICalUserGoodsSourceRateAbility;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceRateDO;
import com.yasyl.sailtotm.domain.userpreference.entity.GoodSourceStaticDO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @program: SailToTm
 * @description:计算用户商品来源偏好实例
 * @author: wujubin
 * @create: 2024-07-19 22:28
 **/
@Service
public class CalUserGoodsSourceRateAbilityInstance implements ICalUserGoodsSourceRateAbility {
    
    @Override
    public GoodSourceRateDO calUserGoodsSourceRate(GoodSourceStaticDO goodSourceStaticDO) {
        int taobaoClickStatic = Math.max(0, goodSourceStaticDO.getTaobaoClickStatic());
        int pddClickStatic = Math.max(0, goodSourceStaticDO.getPddClickStatic());
        int jdClickStatic = Math.max(0, goodSourceStaticDO.getJdClickStatic());

        double sum = taobaoClickStatic + pddClickStatic + jdClickStatic;

        GoodSourceRateDO rateDO = new GoodSourceRateDO();
        rateDO.setGoodSourceStaticDO(goodSourceStaticDO);

        long userId = Optional.ofNullable(goodSourceStaticDO)
                .map(GoodSourceStaticDO::getUserDO)
                .map(UserDO::getUserId)
                .orElse(0L);
        if (userId == 0L) {
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
        rateDO.setUserId(userId);

        int rateSum = 10;
        rateDO.setRateSum(rateSum);

        rateDO.setTaobaoRate((int) ((taobaoClickStatic / sum) * rateSum));
        rateDO.setPddRate((int) ((pddClickStatic / sum) * rateSum));
        rateDO.setJdRate((int) ((jdClickStatic / sum) * rateSum));

        return rateDO;
    }
}
