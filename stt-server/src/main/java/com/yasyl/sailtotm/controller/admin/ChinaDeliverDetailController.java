package com.yasyl.sailtotm.controller.admin;

import com.yasyl.sailtotm.annotation.AdminLicence;
import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.ChinaDeliverDetailDTO;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.ChinaDeliverDetailService;
import com.yasyl.sailtotm.vo.ChinaDeliverDetailVO;
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
