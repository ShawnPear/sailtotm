package com.yasyl.sailtotm.domain.translation.repository;

import com.yasyl.sailtotm.domain.translation.entity.TranslatorDictDO;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 23:26
 **/
public interface ITranslatorDictRepository {
    Boolean insert(TranslatorDictDO dict);

    TranslatorDictDO selectById(Integer id);

    TranslatorDictDO selectByZh(String zh);

    TranslatorDictDO selectByRu(String ru);
}
