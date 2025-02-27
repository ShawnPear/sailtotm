package com.yasyl.sailtotm.controller.user;

import com.yasyl.sailtotm.annotation.CheckUserId;
import com.yasyl.sailtotm.constant.JwtClaimsConstant;
import com.yasyl.sailtotm.dto.UserAccount.UserLoginDTO;
import com.yasyl.sailtotm.dto.UserAccount.UserRegisterDTO;
import com.yasyl.sailtotm.entity.User;
import com.yasyl.sailtotm.exception.user.EmailFormatErrorException;
import com.yasyl.sailtotm.properties.JwtProperties;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.EmailService;
import com.yasyl.sailtotm.service.UserService;
import com.yasyl.sailtotm.utils.EmailUtil;
import com.yasyl.sailtotm.utils.JwtUtil;
import com.yasyl.sailtotm.vo.UserLoginVO;
import com.yasyl.sailtotm.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.yasyl.sailtotm.constant.MessageConstant.*;
import static com.yasyl.sailtotm.enumeration.UserIdIntoType.STRING;

@RestController
@RequestMapping("/user")
@Api(tags = "User账户操作接口")
@Slf4j
public class UserController {

    @Autowired
    EmailService emailService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserService userService;

    @GetMapping("/user/{user_id}")
    @ApiOperation("用户个人信息")
    @CheckUserId(STRING)
    public Result<UserVO> getUser(@PathVariable String user_id) {
        UserVO vo = userService.getByUserId(user_id);
        return Result.success(vo);
    }

    @GetMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO.getEmail());
        log.info("1");
        User user = userService.login(userLoginDTO);
        log.info("2");
        if (user == null) {
            return Result.error(USER_LOGIN_ERROR_NOT_EXIST);
        }
        log.info("3");
        Map<String, Object> claims = new HashMap<>();
        log.info("4");
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .userId(String.valueOf(user.getUserId()))
                .token(token)
                .userName(user.getUserName())
                .build();
        log.info("5");
        return Result.success(userLoginVO, USER_LOGIN_SUCCESS);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册：{}", userRegisterDTO.getEmail());

        Boolean registerStatus = userService.register(userRegisterDTO);

        return Result.status(registerStatus, USER_REGISTER_SUCCESS, USER_REGISTER_ERROR_EMAIL_EXIST);
    }

    @GetMapping("/register/mailRequest")
    @ApiOperation("请求发生验证邮箱")
    public Result mailRequest(String email) {
        if (!EmailUtil.isValidEmail(email))
            throw new EmailFormatErrorException(USER_REGISTER_EMAIL_FORMAT_ERROR);
        String key = userService.generateEmailActivateKey(email);
        emailService.sendHtmlMail(email, EMAIL_VERIFY_TITLE,
                EmailUtil.getActivateEmailContent(email, key)
        );
        return Result.success(SUCCESS);
    }

    @GetMapping("/register/activate")
    @ApiOperation("进行邮箱验证")
    public Result mailActivate(String email, String key) {
        Boolean status = userService.verifyEmailActivateKey(key, email);
        if (status) {
            status = userService.activate(email);
        }
        return Result.status(status);
    }
}
