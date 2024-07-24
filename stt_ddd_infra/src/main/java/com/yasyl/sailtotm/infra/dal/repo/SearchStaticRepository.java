package com.yasyl.sailtotm.infra.dal.repo;

import com.yasyl.sailtotm.domain.goods.recommend.repository.ISearchStaticRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 08:19
 **/
@Service
public class SearchStaticRepository implements ISearchStaticRepository {
    @Override
    public void save(long userId, String keyword) {
        
    }

    @Override
    public List<String> queryTopSearchKeywords(long userId, int size) {
        return Collections.emptyList();
    }
}
