package com.yasyl.sailtotm.app.controllerimpl;

import com.yasyl.sailtotm.client.controller.IUserGoodsSourcePreferenceController;
import com.yasyl.sailtotm.client.dto.request.GoodSourceIncrDTO;
import com.yasyl.sailtotm.client.dto.response.GoodSourceRateDTO;
import com.yasyl.sailtotm.client.dto.result.Result;
import com.yasyl.sailtotm.common.enums.GoodSourceEnum;
import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.common.exception.user.UserGoodSourceException;
import com.yasyl.sailtotm.domain.payment.entity.UserDO;
import com.yasyl.sailtotm.domain.userpreference.model.request.GoodSourceStaticRequest;
import com.yasyl.sailtotm.domain.userpreference.model.response.GoodSourceRateResponse;
import com.yasyl.sailtotm.domain.userpreference.service.IProcessUserGoodsSourceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-20 00:43
 **/
@RestController
@RequestMapping("/user/goods_sources_preference")
@Api(tags = "订单接口")
@Slf4j
public class UserGoodsSourcePreferenceController implements IUserGoodsSourcePreferenceController {
    @Autowired
    IProcessUserGoodsSourceService processUserGoodsSourceService;

    @Override
    @GetMapping("/{user_id}")
    public Result<GoodSourceRateDTO> calUserGoodsSource(@PathVariable String user_id) {
        long userId = Long.parseLong(user_id);
        GoodSourceRateResponse response;
        try {
            response = processUserGoodsSourceService.queryUserGoodsSourceRate(userId);
        } catch (UserGoodSourceException e) {
            response = processUserGoodsSourceService.initUserGoodsSource(userId);
        }
        if (response == null) {
            response = GoodSourceRateResponse.init(userId);
        }
        return Result.success(GoodSourceRateDTO.builder()
                .jdRate(response.getJdRate())
                .pddRate(response.getPddRate())
                .rateSum(response.getRateSum())
                .taobaoRate(response.getTaobaoRate())
                .userId(response.getUserId())
                .build());
    }

    @Override
    @PostMapping("/increase")
    public Result incrUserGoodsSource(@RequestBody GoodSourceIncrDTO goodSourceIncrDTO) {
        long userId = goodSourceIncrDTO.getUserId();
        GoodSourceEnum source = GoodSourceEnum.findByValue(goodSourceIncrDTO.getSource());
        try {
            processUserGoodsSourceService.incrUserGoodsSource(GoodSourceStaticRequest.builder()
                    .userDO(UserDO.builder()
                            .userId(goodSourceIncrDTO.getUserId())
                            .build())
                    .sourceEnum(source)
                    .build());
        } catch (RedisException e) {
            log.error("Redis用户浏览偏好自增Redis错误");
        } catch (UserGoodSourceException e1){
            processUserGoodsSourceService.initUserGoodsSource(userId);
        }
        return Result.success();
    }
}
