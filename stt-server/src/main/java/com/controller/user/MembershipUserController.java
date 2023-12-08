package com.controller.user;

import com.annotation.CheckUserId;
import com.dto.MembershipDTO;
import com.dto.PasswordChangeDTO;
import com.result.Result;
import com.service.MembershipService;
import com.vo.MembershipHistoryVO;
import com.vo.MembershipVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.constant.MessageConstant.*;
import static com.enumeration.UserIdIntoType.CLASS;
import static com.enumeration.UserIdIntoType.STRING;

@RestController
@RequestMapping("/user/membership")
@Api(tags = "会员接口")
@Slf4j
public class MembershipUserController {
    @Autowired
    MembershipService service;

    @GetMapping("/{user_id}")
    @CheckUserId(STRING)
    Result<MembershipVO> getMembership(@PathVariable String user_id) {
        if (user_id == null || user_id.isEmpty())
            return Result.error(PARAM_MISSING_ERROR);
        MembershipVO vo = service.getByUserId(user_id);
        return Result.success(vo);
    }

    @PostMapping
    @CheckUserId(CLASS)
    Result addMembership(@RequestBody MembershipDTO dto) {
        Boolean status = service.add(dto);
        return Result.status(status);
    }

    @PatchMapping
    @CheckUserId(CLASS)
    Result resetPayPassword(@RequestBody PasswordChangeDTO dto) {
        Boolean status = service.resetPassword(dto);
        return Result.status(status);
    }

    @GetMapping("/history/{user_id}")
    @CheckUserId(STRING)
    Result<List<MembershipHistoryVO>> getHistory(@PathVariable String user_id, String status, String page, String page_size) {
        List<MembershipHistoryVO> list = service.getHistoryByUserId(user_id, status, page, page_size);
        return Result.dataDetect(!list.isEmpty(), SUCCESS, NO_DATA, list);
    }
}
