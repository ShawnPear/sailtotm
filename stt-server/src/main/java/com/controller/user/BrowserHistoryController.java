package com.controller.user;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/history")
@Api(tags = "历史记录操作接口")
@Slf4j
public class BrowserHistoryController {
//    @GetMapping
//    public void getBrowserHistory
}
