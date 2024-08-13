package com.yasyl.sailtotm.infra.redis;

import com.yasyl.sailtotm.domain.goods.recommend.repository.IHotBrowserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 09:03
 **/
@Service
public class HotBrowserRepository implements IHotBrowserRepository {
    private static final String prefix = "sailtotm:hotbrowser";
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<String> getOrdered(int page, int size) {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        int begin = page * size, end = begin + size;
        return new ArrayList<>(zSetOperations.reverseRange(prefix, begin, end));
    }

    @Override
    public void save(int numIid) {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.incrementScore(prefix, numIid, 1f);
    }
}
