package com.yasyl.sailtotm.app.job;

import com.yasyl.sailtotm.common.exception.repo.RedisException;
import com.yasyl.sailtotm.domain.userpreference.service.IProcessUserGoodsSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: SailToTm
 * @description: 定时任务同步用户商品浏览偏好
 * @author: wujubin
 * @create: 2024-07-20 00:11
 **/
@Component
@Slf4j
public class SyncUserGoodsSourceTask {
    @Autowired
    IProcessUserGoodsSourceService processUserGoodsSourceService;
    
    @Scheduled(cron = "0/30 * * * * ? *")
    public void sync() {
        try {
            processUserGoodsSourceService.syncUserGoodsSource();
        }catch (RedisException e){
            log.error("用户浏览商品偏好redis->mysql异常");
        }
    }
}
