package com.yasyl.sailtotm.controller.admin;

import com.yasyl.sailtotm.annotation.AdminLicence;
import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.InterestRateDTO;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.InterestRateService;
import com.yasyl.sailtotm.vo.InterestRateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/interest-rate")
@Api(tags = "利息账户操作接口")
@Slf4j
public class InterestRateAdminController {

    @Autowired
    InterestRateService service;

    @PatchMapping
    @AdminLicence
    public Result updateRate(@RequestBody InterestRateDTO dto) {
        Boolean status = service.updateInterestRate(dto);
        return Result.status(status);
    }

    @GetMapping
    public Result<InterestRateVO> getRate() {
        InterestRateVO newestExchangeRate = service.getNewestInterestRate();
        return Result.success(newestExchangeRate, MessageConstant.SUCCESS);
    }
}
