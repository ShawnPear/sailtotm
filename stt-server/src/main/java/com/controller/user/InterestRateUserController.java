package com.controller.user;

import com.constant.MessageConstant;
import com.result.Result;
import com.service.InterestRateService;
import com.vo.InterestRateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/interest-rate")
@Api(tags = "利息账户操作接口")
@Slf4j
public class InterestRateUserController {

    @Autowired
    InterestRateService service;

    @GetMapping
    public Result<InterestRateVO> getRate() {
        InterestRateVO newestExchangeRate = service.getNewestInterestRate();
        return Result.success(newestExchangeRate, MessageConstant.SUCCESS);
    }
}
