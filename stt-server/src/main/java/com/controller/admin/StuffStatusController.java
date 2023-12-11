package com.controller.admin;

import com.annotation.AdminLicence;
import com.dto.Stuff.StuffStatusChangeDTO;
import com.result.Result;
import com.service.StuffService;
import com.vo.StuffInfoVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.constant.MessageConstant.STUFF_STATUS_CHANGE_ERROR;
import static com.constant.MessageConstant.STUFF_STATUS_CHANGE_SUCCESS;

@RestController
@RequestMapping("/admin")
@Api(tags = "admin账户操作接口")
@Slf4j
public class StuffStatusController {
    @Autowired
    StuffService stuffService;

    @PatchMapping(value = {"/stuff-status", "/stuff-role", "/stuff-location"})
    @AdminLicence
    public Result<StuffInfoVO> changeStatus(@RequestBody StuffStatusChangeDTO dto) {
        Boolean status = stuffService.changeStuffStatus(dto);
        if (status) {
            StuffInfoVO stuffInfoVO = StuffInfoVO.builder()
                    .stuff_id(dto.getStuffId())
                    .build();
            return Result.success(stuffInfoVO, STUFF_STATUS_CHANGE_SUCCESS);
        }
        return Result.error(STUFF_STATUS_CHANGE_ERROR);
    }
}
