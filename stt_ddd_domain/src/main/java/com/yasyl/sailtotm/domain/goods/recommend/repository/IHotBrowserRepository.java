package com.yasyl.sailtotm.domain.goods.recommend.repository;

import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 09:03
 **/
public interface IHotBrowserRepository {
    List<String> getOrdered(int page, int size);

    void save(int numIid);
}
