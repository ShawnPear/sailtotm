package com.yasyl.sailtotm.controller.admin;

import com.yasyl.sailtotm.annotation.AdminLicence;
import com.yasyl.sailtotm.dto.TransportTypeDTO;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.TransportTypeService;
import com.yasyl.sailtotm.vo.TransportTypeVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yasyl.sailtotm.constant.MessageConstant.NO_DATA;
import static com.yasyl.sailtotm.constant.MessageConstant.SUCCESS;

@RestController
@RequestMapping("/admin/transport-type")
@Api(tags = "运输方式操作接口")
@Slf4j
public class TransportTypeAdminController {
    @Autowired
    TransportTypeService service;

    @GetMapping
    public Result<List<TransportTypeVO>> getAll() {
        List<TransportTypeVO> all = service.getAll();
        return Result.dataDetect(!all.isEmpty(), SUCCESS, NO_DATA, all);
    }

    @PutMapping
    @AdminLicence
    public Result update(@RequestBody TransportTypeDTO dto) {
        Boolean status = service.update(dto);
        return Result.status(status);
    }

    @PostMapping
    @AdminLicence
    public Result add(@RequestBody TransportTypeDTO dto) {
        Boolean status = service.add(dto);
        return Result.status(status);
    }
}
