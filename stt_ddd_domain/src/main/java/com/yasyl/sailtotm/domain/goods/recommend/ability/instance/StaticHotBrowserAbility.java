package com.yasyl.sailtotm.domain.goods.recommend.ability.instance;

import com.yasyl.sailtotm.domain.goods.recommend.ability.IStaticHotBrowserAbility;
import com.yasyl.sailtotm.domain.goods.recommend.repository.IHotBrowserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 08:48
 **/
@Service
public class StaticHotBrowserAbility implements IStaticHotBrowserAbility {
    @Autowired
    IHotBrowserRepository hotBrowserRepository;
    
    @Override
    public List<String> getHotBrowserNumIid(int page, int size) {
        return hotBrowserRepository.getOrdered(page,size);
    }

    @Override
    public void saveHotBorowserNumIid(int numIid) {
        hotBrowserRepository.save(numIid);
    }
}
