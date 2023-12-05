package com.controller.user;

import com.result.Result;
import com.service.TransportTypeService;
import com.vo.TransportTypeVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.constant.MessageConstant.NO_DATA;
import static com.constant.MessageConstant.SUCCESS;

@RestController
@RequestMapping("/user/transport-type")
@Api(tags = "运输方式操作接口")
@Slf4j
public class TransportTypeUserController {
    @Autowired
    TransportTypeService service;

    @GetMapping
    public Result<List<TransportTypeVO>> getAll() {
        List<TransportTypeVO> all = service.getAll();
        return Result.dataDetect(!all.isEmpty(), SUCCESS, NO_DATA, all);
    }
}
