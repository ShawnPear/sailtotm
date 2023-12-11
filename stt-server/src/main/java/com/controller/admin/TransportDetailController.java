package com.controller.admin;

import com.dto.TransportDetailDTO;
import com.result.Result;
import com.service.TransportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/transport-details")
public class TransportDetailController {

    @Autowired
    TransportDetailService service;

    @PatchMapping("/size")
    public Result modifyGoodSize(@RequestBody TransportDetailDTO dto) {
        Boolean status = service.modifySize(dto);
        return Result.status(status);
    }


}
