package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.entity.PropertiesName;
import com.yasyl.sailtotm.entity.TranslatorDict;

import java.util.List;
import java.util.Map;

public interface SkuPropService {
    String getProp2String(List<String> propertiesList);

    String getPropNameRu2Zh(List<PropertiesName> propertiesNameList);

    Map<String, TranslatorDict> extractSkuToTranDict(Object skuObject, Map<String, TranslatorDict> tranDict);
}
