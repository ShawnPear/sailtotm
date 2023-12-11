package com.controller.admin;

import com.annotation.AdminLicence;
import com.dto.TransportTypeDTO;
import com.result.Result;
import com.service.TransportTypeService;
import com.vo.TransportTypeVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.constant.MessageConstant.NO_DATA;
import static com.constant.MessageConstant.SUCCESS;

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
