package com.controller.admin;

import com.constant.MessageConstant;
import com.dto.TransportTypeDTO;
import com.result.Result;
import com.service.TransportTypeService;
import com.vo.TransportTypeVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (all.isEmpty()) {
            return Result.success(all, MessageConstant.NO_DATA);
        } else {
            return Result.success(all, MessageConstant.SUCCESS);
        }
    }

    @PutMapping
    public Result update(@RequestBody TransportTypeDTO dto) {
        Boolean status = service.update(dto);
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.error(MessageConstant.FAIL);
        }
    }

    @PostMapping
    public Result add(@RequestBody TransportTypeDTO dto) {
        Boolean status = service.add(dto);
        if (status) {
            return Result.success(MessageConstant.SUCCESS);
        } else {
            return Result.error(MessageConstant.FAIL);
        }
    }
}
