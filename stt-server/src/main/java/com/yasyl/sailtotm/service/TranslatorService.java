package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.component.TranslatorMicrosoftPost;
import com.yasyl.sailtotm.entity.TranslatorDict;
import com.yasyl.sailtotm.enumeration.TranslatorType;
import com.yasyl.sailtotm.exception.user.TranslatorException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.yasyl.sailtotm.mapper.TranslatorDictMapper;
import com.yasyl.sailtotm.properties.AzureTranslatorProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import static com.yasyl.sailtotm.constant.MessageConstant.TRANSLATOR_ERROR;

@Slf4j
public abstract class TranslatorService {
    @Autowired
    AzureTranslatorProperties translatorProperties;
    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private TranslatorMicrosoftPost translatorPost;
    @Autowired
    private TranslatorDictMapper translatorDictMapper;

    public abstract List<String> translator(List<String> source, TranslatorType type);

    public abstract List<?> translator(List<?> source, TranslatorType type, Class<?> translatorType);

    public String translator(String source, TranslatorType type) {
        List<String> list = new ArrayList<>();
        list.add(source);
        return translator(list, type).get(0);
    }

    public Map<String, TranslatorDict> translator(Map<String, TranslatorDict> source, TranslatorType type) {
        Set<Map.Entry<String, TranslatorDict>> entries = source.entrySet();
        List<String> list = new ArrayList<>();
        if (type == TranslatorType.ZH2RU) {
            for (Map.Entry<String, TranslatorDict> me : entries) {
                list.add(me.getValue().getZh());
            }
        } else if (type == TranslatorType.RU2ZH) {
            for (Map.Entry<String, TranslatorDict> me : entries) {
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
            TranslatorDict byZh = translatorDictMapper.selectByZh(source);
            if (byZh == null || byZh.getRu() == null || byZh.getRu().isEmpty()) {
                String translator = translator(source, type);
                new Thread(() -> translatorDictMapper.insert(TranslatorDict.builder().zh(source).ru(translator).build())).start();
                return translator;
            }
            return byZh.getRu();
        } else if (type == TranslatorType.RU2ZH) {
            TranslatorDict byRu = translatorDictMapper.selectByRu(source);
            if (byRu == null || byRu.getZh() == null || byRu.getZh().isEmpty()) {
                String translator = translator(source, type);
                new Thread(() -> translatorDictMapper.insert(TranslatorDict.builder().ru(source).zh(translator).build())).start();
                log.info("调用了翻译器，RU2ZH，source：{},answer:{}", source, translator);
                return translator;
            }
            return byRu.getZh();
        }
        return null;
    }

    public Map<String, TranslatorDict> translatorCache(Map<String, TranslatorDict> source, TranslatorType type) {
        try {
            List<TranslatorDict> list = new CopyOnWriteArrayList<>();
            CountDownLatch latch;
            if (type == TranslatorType.ZH2RU) {
                Set<Map.Entry<String, TranslatorDict>> entries = source.entrySet();
                latch = new CountDownLatch(entries.size());
                for (Map.Entry<String, TranslatorDict> me : entries) {
                    new Thread(() -> {
                        TranslatorDict byZh = translatorDictMapper.selectByZh(me.getValue().getZh());
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
                Set<Map.Entry<String, TranslatorDict>> entries = source.entrySet();
                latch = new CountDownLatch(entries.size());
                for (Map.Entry<String, TranslatorDict> me : entries) {
                    new Thread(() -> {
                        TranslatorDict byRu = translatorDictMapper.selectByRu(me.getValue().getRu());
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

            translator(list, type, TranslatorDict.class);

            insertTranMapIntoDB(list);

            return source;
        } catch (InterruptedException e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }

    public Boolean insertTranMapIntoDB(Map<String, TranslatorDict> tranDict) {
        CountDownLatch translatorMapperLatch = new CountDownLatch(tranDict.size());

        Set<Map.Entry<String, TranslatorDict>> tranDictEntry = tranDict.entrySet();
        for (Map.Entry<String, TranslatorDict> me : tranDictEntry) {
            new Thread(() -> {
                translatorDictMapper.insert(me.getValue());
                translatorMapperLatch.countDown();
            }).start();
        }

        try {
            translatorMapperLatch.await();
            return true;
        } catch (InterruptedException e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }

    public Boolean insertTranMapIntoDB(List<TranslatorDict> tranDict) {
        CountDownLatch translatorMapperLatch = new CountDownLatch(tranDict.size());

        for (TranslatorDict me : tranDict) {
            new Thread(() -> {
                translatorDictMapper.insert(me);
                translatorMapperLatch.countDown();
            }).start();
        }

        try {
            translatorMapperLatch.await();
            return true;
        } catch (InterruptedException e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }
}
