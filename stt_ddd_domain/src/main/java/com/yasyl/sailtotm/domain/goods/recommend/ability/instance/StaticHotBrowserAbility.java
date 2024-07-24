package com.yasyl.sailtotm.domain.goods.recommend.ability.instance;

import com.yasyl.sailtotm.domain.goods.recommend.ability.IStaticHotBrowserAbility;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 08:48
 **/
@Service
public class StaticHotBrowserAbility implements IStaticHotBrowserAbility {
    @Override
    public List<String> getHotBrowserId(int page, int size) {
        return Collections.emptyList();
    }
}
