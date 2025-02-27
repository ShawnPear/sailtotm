package com.yasyl.sailtotm.controller.admin;

import com.yasyl.sailtotm.annotation.AdminLicence;
import com.yasyl.sailtotm.constant.JwtClaimsConstant;
import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.Stuff.StuffLoginDTO;
import com.yasyl.sailtotm.dto.Stuff.StuffRegisterDTO;
import com.yasyl.sailtotm.entity.Stuff;
import com.yasyl.sailtotm.properties.JwtProperties;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.StuffService;
import com.yasyl.sailtotm.utils.JwtUtil;
import com.yasyl.sailtotm.vo.StuffLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.yasyl.sailtotm.constant.MessageConstant.STUFF_REGISTER_ERROR_EMAIL_EXIST;
import static com.yasyl.sailtotm.constant.MessageConstant.STUFF_REGISTER_SUCCESS;

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
        claims.put(JwtClaimsConstant.ADMIN_ROLE_ID, stuff.getRoleId());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        StuffLoginVO stuffLoginVO = StuffLoginVO.builder()
                .stuffid(String.valueOf(stuff.getStuffId()))
                .token(token)
                .roleid(String.valueOf(stuff.getRoleId()))
                .lastName(stuff.getLastName())
                .firstName(stuff.getFirstName())
                .build();

        return Result.success(stuffLoginVO, MessageConstant.STUFF_LOGIN_SUCCESS);
    }

    @PostMapping("/register")
    @ApiOperation("管理员注册")
    @AdminLicence
    public Result register(@RequestBody StuffRegisterDTO stuffRegisterDTO) {
        log.info("管理员注册：{}", stuffRegisterDTO.getEmail());

        Boolean registerStatus = stuffService.register(stuffRegisterDTO);

        return Result.status(registerStatus,STUFF_REGISTER_SUCCESS,STUFF_REGISTER_ERROR_EMAIL_EXIST);
    }
}
