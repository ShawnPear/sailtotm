package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.annotation.CheckUserId;
import com.yasyl.sailtotm.dto.MembershipDTO;
import com.yasyl.sailtotm.dto.MembershipPayDTO;
import com.yasyl.sailtotm.dto.PasswordChangeDTO;
import com.yasyl.sailtotm.dto.PayDTO;
import com.yasyl.sailtotm.enumeration.PayType;
import com.yasyl.sailtotm.enumeration.StuffStatusType;
import com.yasyl.sailtotm.exception.user.BaseException;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.MembershipService;
import com.yasyl.sailtotm.service.OrderService;
import com.yasyl.sailtotm.vo.MembershipHistoryVO;
import com.yasyl.sailtotm.vo.MembershipVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yasyl.sailtotm.constant.MessageConstant.*;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.CLASS;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.STRING;

@RestController
@RequestMapping("/user/membership")
@Api(tags = "会员接口")
@Slf4j
public class MembershipUserController {
    @Autowired
    MembershipService membershipService;

    @Autowired
    OrderService orderService;

    @GetMapping("/{user_id}")
    @CheckUserId(STRING)
    Result<MembershipVO> getMembership(@PathVariable String user_id) {
        if (user_id == null || user_id.isEmpty())
            return Result.error(PARAM_MISSING_ERROR);
        MembershipVO vo = membershipService.getByUserId(user_id);
        return Result.success(vo);
    }

    @PostMapping
    @CheckUserId(CLASS)
    Result addMembership(@RequestBody MembershipDTO dto) {
        Boolean status = membershipService.add(dto);
        return Result.status(status);
    }

    @PatchMapping
    @CheckUserId(CLASS)
    Result resetPayPassword(@RequestBody PasswordChangeDTO dto) {
        Boolean status = membershipService.resetPassword(dto);
        return Result.status(status);
    }

    @GetMapping("/history/{user_id}")
    @CheckUserId(STRING)
    Result<List<MembershipHistoryVO>> getHistory(@PathVariable String user_id, String status, String page, String page_size) {
        List<MembershipHistoryVO> list = membershipService.getHistoryByUserId(user_id, status, page, page_size);
        return Result.dataDetect(!list.isEmpty(), SUCCESS, NO_DATA, list);
    }

    @PostMapping("/pay")
    @CheckUserId(CLASS)
    Result payByMembership(@RequestBody PayDTO dto) {
        dto.setPayType(PayType.MEMBERSHIP);
        dto.setStuffId(StuffStatusType.SELF_SERVICE_STUFF);
        MembershipPayDTO membershipPayDTO = MembershipPayDTO.builder().build();
        BeanUtils.copyProperties(dto, membershipPayDTO);
        membershipPayDTO.setDefaultStuff(dto.getStuffId());
        Boolean status = membershipService.pay(membershipPayDTO);

        if (!status) throw new BaseException(FAIL);
        status &= orderService.pay(dto);
        if (!status) throw new BaseException(FAIL);
        status &= orderService.beginOrderTransactional(dto.getOrderId(), dto.getUserId(), dto.getStuffId());
        return Result.status(status);
    }
}
