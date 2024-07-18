package com.yasyl.sailtotm.controller.user;


import com.yasyl.sailtotm.exception.user.IdNotExistException;
import com.yasyl.sailtotm.result.Result;
import com.yasyl.sailtotm.service.StoreLocationService;
import com.yasyl.sailtotm.vo.StoreLocationVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.yasyl.sailtotm.constant.MessageConstant.FAIL;
import static com.yasyl.sailtotm.constant.MessageConstant.SUCCESS;

@RestController
@RequestMapping("/user/store-location")
@Api(tags = "门店账户操作接口")
@Slf4j
public class StoreLocationUserController {
    @Autowired
    StoreLocationService service;

    @GetMapping
    public Result<List<StoreLocationVO>> getAll(String location_id, String page, String page_size) {
        List<StoreLocationVO> all = new ArrayList<>();
        if (location_id != null && !location_id.isEmpty()) {
            try {
                StoreLocationVO add = service.getAllById(location_id);
                all.add(add);
            } catch (Exception e) {
                throw new IdNotExistException(FAIL);
            }
        } else {
            all = service.getAllPage(page, page_size);
        }
        return Result.success(all, SUCCESS);
    }
}
