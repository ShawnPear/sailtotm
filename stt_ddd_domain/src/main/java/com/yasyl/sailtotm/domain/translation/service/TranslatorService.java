package com.yasyl.sailtotm.domain.translation.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.yasyl.sailtotm.common.enumeration.TranslatorType;
import com.yasyl.sailtotm.common.exception.user.TranslatorException;
import com.yasyl.sailtotm.domain.translation.entity.TranslatorDictDO;
import com.yasyl.sailtotm.domain.translation.repository.ITranslatorDictRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import static com.yasyl.sailtotm.common.constant.MessageConstant.TRANSLATOR_ERROR;


@Slf4j
public abstract class TranslatorService {
    @Autowired
    JsonMapper jsonMapper;

    @Autowired
    ITranslatorDictRepository translatorDictRepository;

    @Qualifier("translatorSyncThreadPool")
    @Autowired
    private ThreadPoolTaskExecutor userGoodsPreferenceSyncThreadPool;

    public abstract List<String> translator(List<String> source, TranslatorType type);

    public abstract List<?> translator(List<?> source, TranslatorType type, Class<?> translatorType);

    public String translator(String source, TranslatorType type) {
        List<String> list = new ArrayList<>();
        list.add(source);
        return translator(list, type).get(0);
    }

    public Map<String, TranslatorDictDO> translator(Map<String, TranslatorDictDO> source, TranslatorType type) {
        Set<Map.Entry<String, TranslatorDictDO>> entries = source.entrySet();
        List<String> list = new ArrayList<>();
        if (type == TranslatorType.ZH2RU) {
            for (Map.Entry<String, TranslatorDictDO> me : entries) {
                list.add(me.getValue().getZh());
            }
        } else if (type == TranslatorType.RU2ZH) {
            for (Map.Entry<String, TranslatorDictDO> me : entries) {
                list.add(me.getValue().getRu());
            }
        }
        List<String> rawList = new ArrayList<>(list);
        list = translator(list, type);
        int iList = 0;
        if (type == TranslatorType.ZH2RU) {
            for (String raw : rawList) {
                source.get(raw).setRu(list.get(iList++));
            }
        } else if (type == TranslatorType.RU2ZH) {
            for (String raw : rawList) {
                source.get(raw).setZh(list.get(iList++));
            }
        }
        return source;
    }

    public String translatorCache(String source, TranslatorType type) {
        if (type == TranslatorType.ZH2RU) {
            TranslatorDictDO byZh = translatorDictRepository.selectByZh(source);
            if (byZh == null || byZh.getRu() == null || byZh.getRu().isEmpty()) {
                String translator = translator(source, type);
                new Thread(() -> translatorDictRepository.insert(TranslatorDictDO.builder().zh(source).ru(translator).build())).start();
                return translator;
            }
            return byZh.getRu();
        } else if (type == TranslatorType.RU2ZH) {
            TranslatorDictDO byRu = translatorDictRepository.selectByRu(source);
            if (byRu == null || byRu.getZh() == null || byRu.getZh().isEmpty()) {
                String translator = translator(source, type);
                new Thread(() -> translatorDictRepository.insert(TranslatorDictDO.builder().ru(source).zh(translator).build())).start();
                log.info("调用了翻译器，RU2ZH，source：{},answer:{}", source, translator);
                return translator;
            }
            return byRu.getZh();
        }
        return null;
    }

    public Map<String, TranslatorDictDO> translatorCache(Map<String, TranslatorDictDO> source, TranslatorType type) {
        try {
            List<TranslatorDictDO> list = new CopyOnWriteArrayList<>();
            CountDownLatch latch;
            if (type == TranslatorType.ZH2RU) {
                Set<Map.Entry<String, TranslatorDictDO>> entries = source.entrySet();
                latch = new CountDownLatch(entries.size());
                for (Map.Entry<String, TranslatorDictDO> me : entries) {
                    new Thread(() -> {
                        TranslatorDictDO byZh = translatorDictRepository.selectByZh(me.getValue().getZh());
                        if (byZh == null || byZh.getRu() == null || byZh.getRu().isEmpty()) {
                            list.add(me.getValue());
                        } else {
                            me.getValue().setTranslatorId(byZh.getTranslatorId());
                            me.getValue().setRu(byZh.getRu());
                        }
                        latch.countDown();
                    }).start();
                }
            } else if (type == TranslatorType.RU2ZH) {
                Set<Map.Entry<String, TranslatorDictDO>> entries = source.entrySet();
                latch = new CountDownLatch(entries.size());
                for (Map.Entry<String, TranslatorDictDO> me : entries) {
                    new Thread(() -> {
                        TranslatorDictDO byRu = translatorDictRepository.selectByRu(me.getValue().getRu());
                        if (byRu == null || byRu.getZh() == null || byRu.getZh().isEmpty()) {
                            list.add(me.getValue());
                        } else {
                            me.getValue().setTranslatorId(byRu.getTranslatorId());
                            me.getValue().setZh(byRu.getZh());
                        }
                        latch.countDown();
                    }).start();
                }
            } else {
                latch = null;
            }
            latch.await();

            translator(list, type, TranslatorDictDO.class);

            insertTranMapIntoDB(list);

            return source;
        } catch (InterruptedException e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }

    public Boolean insertTranMapIntoDB(Map<String, TranslatorDictDO> tranDict) {
        CountDownLatch translatorMapperLatch = new CountDownLatch(tranDict.size());

        Set<Map.Entry<String, TranslatorDictDO>> tranDictEntry = tranDict.entrySet();
        for (Map.Entry<String, TranslatorDictDO> me : tranDictEntry) {
            userGoodsPreferenceSyncThreadPool.execute(() -> {
                translatorDictRepository.insert(me.getValue());
                translatorMapperLatch.countDown();
            });
        }

        try {
            translatorMapperLatch.await();
            return true;
        } catch (InterruptedException e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }

    public Boolean insertTranMapIntoDB(List<TranslatorDictDO> tranDict) {
        CountDownLatch translatorMapperLatch = new CountDownLatch(tranDict.size());

        for (TranslatorDictDO me : tranDict) {
            userGoodsPreferenceSyncThreadPool.execute(() -> {
                translatorDictRepository.insert(me);
                translatorMapperLatch.countDown();
            });
        }

        try {
            translatorMapperLatch.await();
            return true;
        } catch (InterruptedException e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }
}
