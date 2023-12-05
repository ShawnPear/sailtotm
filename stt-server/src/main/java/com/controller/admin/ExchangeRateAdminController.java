package com.controller.admin;

import com.constant.MessageConstant;
import com.dto.ExchangeRateDTO;
import com.result.Result;
import com.service.ExchangeRateService;
import com.vo.ExchangeRateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/exchange-rate")
@Api(tags = "汇率账户操作接口")
@Slf4j
public class ExchangeRateAdminController {

    @Autowired
    ExchangeRateService service;

    @PatchMapping
    public Result updateRate(@RequestBody ExchangeRateDTO dto) {
        Boolean status = service.updateExchangeRate(dto);
        return Result.status(status);
    }

    @GetMapping
    public Result<ExchangeRateVO> getRate() {
        ExchangeRateVO newestExchangeRate = service.getNewestExchangeRate();
        return Result.success(newestExchangeRate, MessageConstant.SUCCESS);
    }
}
