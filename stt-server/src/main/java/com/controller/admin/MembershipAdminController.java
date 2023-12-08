package com.controller.admin;

import com.dto.AdminTopUpDto;
import com.result.Result;
import com.service.MembershipService;
import com.vo.MembershipVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.constant.MessageConstant.PARAM_MISSING_ERROR;

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

    @GetMapping("/{user_id}")
    public Result<MembershipVO> getMember(@PathVariable String user_id) {
        if (user_id == null || user_id.isEmpty())
            return Result.error(PARAM_MISSING_ERROR);
        MembershipVO vo = service.getByUserId(user_id);
        return Result.success(vo);
    }
}
