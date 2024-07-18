package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.dto.UserAccount.UserResetEmailDTO;
import com.yasyl.sailtotm.dto.UserAccount.UserResetPasswordDTO;
import com.yasyl.sailtotm.dto.UserAccount.UserResetUserDTO;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.yasyl.sailtotm.constant.MessageConstant.*;

@RestController
@RequestMapping("/user/reset")
@Api(tags = "User账户信息重置相关接口操作接口")
@Slf4j
public class ResetAccountController {
    @Autowired
    UserService userService;

    @PatchMapping("/password/{user_id}")
    @ApiOperation("重置密码")
    public Result resetPassword(@PathVariable String user_id, @RequestBody UserResetPasswordDTO dto) {
        dto.setUserId(user_id);
        Boolean status = userService.resetPassword(dto);
        return Result.status(status, USER_RESET_PASSWORD_SUCCESS, USER_RESET_PASSWORD_ERROR);
    }

    @PatchMapping("/user_data/{user_id}")
    @ApiOperation("修改用户信息")
    public Result resetUserData(@PathVariable String user_id, @RequestBody UserResetUserDTO dto) {
        dto.setUserId(user_id);
        Boolean status = userService.updateUserInfo(dto);
        return Result.status(status, USER_RESET_USER_SUCCESS, USER_RESET_USER_ERROR);

    }

    @PatchMapping("/email/{user_id}")
    @ApiOperation("修改邮箱")
    public Result resetEmail(@PathVariable String user_id, @RequestBody UserResetEmailDTO dto) {
        dto.setUserId(user_id);
        Boolean status = userService.resetEmail(dto);
        return Result.status(status, USER_RESET_EMAIL_SUCCESS, USER_RESET_EMAIL_ERROR);
    }

//    @GetMapping("/new-password")
//    @ApiOperation("重置密码")
//    public Result newPassword(){
//
//    }

//    @GetMapping("/new-memberhsip-password")
//    @ApiOperation("重置会员密码")
//    public Result newMemberhsipPassword() {
//
//    }
}
