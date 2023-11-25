package com.controller.admin;

import com.constant.JwtClaimsConstant;
import com.constant.MessageConstant;
import com.dto.StuffLoginDTO;
import com.dto.StuffRegisterDTO;
import com.entity.Stuff;
import com.properties.JwtProperties;
import com.result.Result;
import com.service.StuffService;
import com.utils.JwtUtil;
import com.vo.StuffLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(tags = "admin账户操作接口")
@Slf4j
public class StuffController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StuffService stuffService;

    @GetMapping("/login")
    @ApiOperation("管理员登录")
    public Result<StuffLoginVO> login(StuffLoginDTO stuffLoginDTO) {
        log.info("管理员登录：{}", stuffLoginDTO.getEmail());

        Stuff stuff = stuffService.login(stuffLoginDTO);

        if (stuff == null) {
            return Result.error(MessageConstant.STUFF_LOGIN_ERROR_NOT_EXIST);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, stuff.getStuffId());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        StuffLoginVO stuffLoginVO = StuffLoginVO.builder()
                .stuffid(String.valueOf(stuff.getStuffId()))
                .token(token)
                .roleid(String.valueOf(stuff.getRoleId()))
                .lastName(stuff.getName().getLastName())
                .firstName(stuff.getName().getFirstName())
                .build();

        return Result.success(stuffLoginVO, MessageConstant.STUFF_LOGIN_SUCCESS);
    }

    @PostMapping("/register")
    @ApiOperation("管理员注册")
    public Result register(@RequestBody StuffRegisterDTO stuffRegisterDTO) {
        log.info("管理员注册：{}", stuffRegisterDTO.getEmail());

        Boolean registerStatus = stuffService.register(stuffRegisterDTO);

        return registerStatus
                ? Result.success(MessageConstant.STUFF_REGISTER_SUCCESS)
                : Result.success(MessageConstant.STUFF_REGISTER_ERROR_EMAIL_EXIST);
    }
}
