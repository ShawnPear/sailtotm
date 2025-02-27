package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.ExchangeRateService;
import com.yasyl.sailtotm.vo.ExchangeRateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/exchange-rate")
@Api(tags = "汇率账户操作接口")
@Slf4j
public class ExchangeRateUserController {

    @Autowired
    ExchangeRateService service;

    @GetMapping
    public Result<ExchangeRateVO> getRate() {
        ExchangeRateVO newestExchangeRate = service.getNewestExchangeRate();
        return Result.success(newestExchangeRate, MessageConstant.SUCCESS);
    }
}
