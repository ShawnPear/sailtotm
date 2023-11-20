package com.controller.user;

import com.constant.JwtClaimsConstant;
import com.constant.MessageConstant;
import com.dto.UserLoginDTO;
import com.entity.User;
import com.properties.JwtProperties;
import com.result.Result;
import com.service.UserService;
import com.utils.JwtUtil;
import com.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "User账户操作接口")
@Slf4j
public class UserController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO.getEmail());

        User user = userService.login(userLoginDTO);

        if (user == null) {
            return Result.error(MessageConstant.USER_LOGIN_ERROR_NOT_EXIST);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserid());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .user_id(String.valueOf(user.getUserid()))
                .token(token)
                .user_name(user.getUserName())
                .build();

        return Result.success(userLoginVO, MessageConstant.USER_LOGIN_SUCCESS);
    }
}
