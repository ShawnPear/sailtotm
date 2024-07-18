package com.yasyl.sailtotm.service.impl.TranslatorImpl;

import com.yasyl.sailtotm.component.TranslatorYoudaoPost;
import com.yasyl.sailtotm.entity.TranslatorDict;
import com.yasyl.sailtotm.enumeration.TranslatorType;
import com.yasyl.sailtotm.exception.user.TranslatorException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.yasyl.sailtotm.properties.TranslatorProperties;
import com.yasyl.sailtotm.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.yasyl.sailtotm.constant.MessageConstant.TRANSLATOR_ERROR;

@Service
@ConditionalOnProperty(name = TranslatorProperties.YML_TRANSLATOR_USE, havingValue = TranslatorProperties.YOUDAO)
public class TranslatorYoudao extends TranslatorService {

    @Autowired
    JsonMapper jsonMapper;

    @Autowired
    TranslatorYoudaoPost translatorPost;

    @Override
    public List<String> translator(List<String> source, TranslatorType type) {
        for (int i = 0; i < source.size(); i++) {
            if (source.get(i) == null) {
                source.set(i, "");
            }
        }
        try {
            List<String> temp =  translatorPost.translator(type, source.toArray(new String[0]));
            for (int i = 0; i < source.size(); i++) {
                source.set(i, temp.get(i));
            }
            return source;
        } catch (Exception e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
    }

    @Override
    public List<?> translator(List<?> source, TranslatorType type, Class<?> translatorType) {
        if (source == null || source.isEmpty()) return new ArrayList<>();
        List<String> source2 = new ArrayList<>();
        if (translatorType == String.class) {
            source2 = (List<String>) source;
        } else if (translatorType == TranslatorDict.class) {
            if (type == TranslatorType.ZH2RU) {
                for (Object o : source) {
                    TranslatorDict dict = (TranslatorDict) o;
                    source2.add(dict.getZh());
                }
            } else if (type == TranslatorType.RU2ZH) {
                for (Object o : source) {
                    TranslatorDict dict = (TranslatorDict) o;
                    source2.add(dict.getRu());
                }
            }
        }
        try {
            source2 = translatorPost.translator(type, source2.toArray(new String[0]));
            if (translatorType == String.class) {
                return source2;
            } else if (translatorType == TranslatorDict.class) {
                if (type == TranslatorType.ZH2RU) {
                    for (int i = 0; i < source2.size(); i++) {
                        TranslatorDict dict = (TranslatorDict) source.get(i);
                        dict.setRu(source2.get(i));
                    }
                } else if (type == TranslatorType.RU2ZH) {
                    for (int i = 0; i < source2.size(); i++) {
                        TranslatorDict dict = (TranslatorDict) source.get(i);
                        dict.setZh(source2.get(i));
                    }
                }
                return source;
            }
        } catch (Exception e) {
            throw new TranslatorException(TRANSLATOR_ERROR);
        }
        return source;
    }
}
