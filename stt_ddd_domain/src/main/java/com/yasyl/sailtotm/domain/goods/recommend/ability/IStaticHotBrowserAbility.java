package com.yasyl.sailtotm.domain.goods.recommend.ability;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 08:48
 **/
public interface IStaticHotBrowserAbility {
    List<String> getHotBrowserId(int page, int size);
}
