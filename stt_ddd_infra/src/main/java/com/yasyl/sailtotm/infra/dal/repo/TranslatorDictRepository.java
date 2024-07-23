package com.yasyl.sailtotm.infra.dal.repo;

import com.yasyl.sailtotm.domain.translation.entity.TranslatorDictDO;
import com.yasyl.sailtotm.domain.translation.repository.ITranslatorDictRepository;
import com.yasyl.sailtotm.infra.dal.dao.TranslatorDictMapper;
import com.yasyl.sailtotm.infra.dal.entity.TranslatorDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 23:25
 **/
@Service
public class TranslatorDictRepository implements ITranslatorDictRepository {
    @Autowired
    TranslatorDictMapper mapper;

    @Override
    public Boolean insert(TranslatorDictDO dict) {
        return mapper.insert(TranslatorDict.builder()
                .ru(dict.getRu())
                .zh(dict.getZh())
                .build());
    }

    @Override
    public TranslatorDictDO selectById(Integer id) {
        TranslatorDict dict = mapper.selectById(id);
        if (dict == null) {
            return null;
        }
        return TranslatorDictDO.builder()
                .translatorId(dict.getTranslatorId())
                .ru(dict.getRu())
                .zh(dict.getZh())
                .build();
    }

    @Override
    public TranslatorDictDO selectByZh(String zh) {
        TranslatorDict dict = mapper.selectByZh(zh);
        if (dict == null) {
            return null;
        }
        return TranslatorDictDO.builder()
                .translatorId(dict.getTranslatorId())
                .ru(dict.getRu())
                .zh(dict.getZh())
                .build();
    }

    @Override
    public TranslatorDictDO selectByRu(String ru) {
        TranslatorDict dict = mapper.selectByRu(ru);
        if (dict == null) {
            return null;
        }
        return TranslatorDictDO.builder()
                .translatorId(dict.getTranslatorId())
                .ru(dict.getRu())
                .zh(dict.getZh())
                .build();
    }
}
