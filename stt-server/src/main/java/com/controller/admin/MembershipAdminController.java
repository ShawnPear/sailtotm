package com.controller.admin;

import com.dto.AdminTopUpDto;
import com.result.Result;
import com.service.MembershipService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/membership")
@Api(tags = "会员接口")
@Slf4j
public class MembershipAdminController {
    @Autowired
    MembershipService service;

    @PatchMapping("/top_up")
    public Result topUp(@RequestBody AdminTopUpDto dto) {
        Boolean status = service.topUp(dto);
        return Result.status(status);
    }
}
