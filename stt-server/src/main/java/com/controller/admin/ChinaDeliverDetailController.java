package com.controller.admin;

import com.annotation.AdminLicence;
import com.constant.MessageConstant;
import com.dto.ChinaDeliverDetailDTO;
import com.result.Result;
import com.service.ChinaDeliverDetailService;
import com.vo.ChinaDeliverDetailVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/china-deliver-info")
@Api(tags = "汇率账户操作接口")
@Slf4j
public class ChinaDeliverDetailController {
    @Autowired
    ChinaDeliverDetailService service;

    @GetMapping
    public Result<List<ChinaDeliverDetailVO>> getAll() {
        List<ChinaDeliverDetailVO> all = service.getAll();
        return Result.success(all, MessageConstant.SUCCESS);
    }

    @PostMapping
    @AdminLicence
    private Result add(@RequestBody ChinaDeliverDetailDTO dto) {
        dto.setStatus("1");
        Boolean status = service.add(dto);
        return Result.status(status);
    }

    @PatchMapping
    @AdminLicence
    public Result update(@RequestBody ChinaDeliverDetailDTO dto) {
        Boolean status = service.update(dto);
        return Result.status(status);
    }
}
