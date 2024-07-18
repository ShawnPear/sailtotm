package com.yasyl.sailtotm.controller.admin;

import com.yasyl.sailtotm.annotation.AdminLicence;
import com.yasyl.sailtotm.dto.Stuff.StuffStatusChangeDTO;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.StuffService;
import com.yasyl.sailtotm.vo.StuffInfoVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yasyl.sailtotm.constant.MessageConstant.STUFF_STATUS_CHANGE_ERROR;
import static com.yasyl.sailtotm.constant.MessageConstant.STUFF_STATUS_CHANGE_SUCCESS;

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
