package com.service;

import com.entity.TranslatorDict;
import com.enumeration.TranslatorType;

import java.util.List;
import java.util.Map;

public interface TranslatorService {
    public List<String> translator(List<String> source, TranslatorType type);
    public List<?> translator(List<?> source, TranslatorType type, Class<?> translatorType);

    public String translator(String source, TranslatorType type);

    public Map<String, TranslatorDict> translator(Map<String, TranslatorDict> source, TranslatorType type);

    public String translatorCache(String source, TranslatorType type);
    public Map<String, TranslatorDict> translatorCache(Map<String, TranslatorDict> source, TranslatorType type);
    public Boolean insertTranMapIntoDB(Map<String, TranslatorDict> tranDict);

    public Boolean insertTranMapIntoDB(List<TranslatorDict> tranDict);
}
