package com.yasyl.sailtotm.controller.admin;


import com.yasyl.sailtotm.annotation.AdminLicence;
import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.StoreLocationDTO;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.StoreLocationService;
import com.yasyl.sailtotm.vo.StoreLocationVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/store-location")
@Api(tags = "门店账户操作接口")
@Slf4j
public class StoreLocationAdminController {
    @Autowired
    StoreLocationService service;

    @PutMapping
    @AdminLicence
    public Result update(@RequestBody StoreLocationDTO dto) {
        Boolean status = service.update(dto);
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.error(MessageConstant.FAIL);
        }
    }

    @PostMapping
    @AdminLicence
    public Result insert(@RequestBody StoreLocationDTO dto) {
        Boolean status = service.add(dto);
        return Result.status(status);
    }

    @GetMapping
    public Result<List<StoreLocationVO>> getAll() {
        List<StoreLocationVO> all = service.getAll();
        return Result.success(all, MessageConstant.SUCCESS);
    }
}
