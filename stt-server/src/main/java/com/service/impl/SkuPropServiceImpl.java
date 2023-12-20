package com.service.impl;

import com.entity.PropertiesName;
import com.entity.TranslatorDict;
import com.enumeration.TranslatorType;
import com.exception.user.BaseException;
import com.mapper.TranslatorDictMapper;
import com.service.SkuPropService;
import com.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.constant.MessageConstant.NO_METHOED;

@Service
public class SkuPropServiceImpl implements SkuPropService {
    @Autowired
    TranslatorDictMapper translatorDictMapper;

    @Autowired
    TranslatorService translatorService;

    @Override
    public String getProp2String(List<String> propertiesList) {
        StringBuffer sb = new StringBuffer(propertiesList.get(0));
        for (int i = 1; i < propertiesList.size(); i++) {
            sb.append(";");
            sb.append(propertiesList.get(i));
        }
        return sb.toString();
    }

    @Override
    public String getPropNameRu2Zh(List<PropertiesName> propertiesNameList) {
        StringBuffer sb = new StringBuffer();
        PropertiesName name = propertiesNameList.get(0);
        tranNameAppendString(name, sb);
        for (int i = 1; i < propertiesNameList.size(); i++) {
            name = propertiesNameList.get(i);
            sb.append(";");
            tranNameAppendString(name, sb);
        }
        return sb.toString();
    }

    @Override
    public Map<String, TranslatorDict> extractSkuToTranDict(Object skuObject, Map<String, TranslatorDict> tranDict) {
        Class<?> aClass = skuObject.getClass();
        Method getProperties, getPropertiesName, setPropertiesNameList, setPropertiesList;
        try {
            getProperties = aClass.getMethod("getProperties");
            getPropertiesName = aClass.getMethod("getPropertiesName");
            setPropertiesList = aClass.getMethod("setPropertiesList", List.class);
            setPropertiesNameList = aClass.getMethod("setPropertiesNameList", List.class);
            String properties = (String) getProperties.invoke(skuObject);
            String propertiesName = (String) getPropertiesName.invoke(skuObject);
            List<String> pId = new ArrayList<>(Arrays.asList(properties.split(";")));
            List<PropertiesName> tranP = new ArrayList<>();
            setPropertiesNameList.invoke(skuObject,tranP);
            setPropertiesList.invoke(skuObject,pId);
            String[] pValList = propertiesName.split(";");
            for (int j = 0; j < pValList.length; j++) {
                tranP.add(PropertiesName.builder().build());

                String pVal = pValList[j];

                String[] tempVal = pVal.split(":");
                String prop = tempVal[0] + ":" + tempVal[1];
                String val = tempVal[2] + ":" + tempVal[3];

                if (tranDict.get(val) == null)
                    tranDict.put(val, TranslatorDict.builder().zh(val).build());

                tranP.get(j).setProperties(prop);
                tranP.get(j).setPropertiesNameItem(tranDict.get(val));
            }
            return tranDict;
        } catch (Exception e) {
            throw new BaseException(NO_METHOED);
        }
    }

    private void tranNameAppendString(PropertiesName name, StringBuffer sb) {
        TranslatorDict translatorDict = name.getPropertiesNameItem();
        String propVal = "";
        if (translatorDict.getZh().isEmpty()) {
            propVal = translatorDict.getZh();
        } else {
            TranslatorDict dict = translatorDictMapper.selectById(translatorDict.getTranslatorId());
            if (dict != null) {
                propVal = dict.getZh();
            } else {
                propVal = translatorService.translatorCache(dict.getRu(), TranslatorType.RU2ZH);
            }
        }
        sb.append(name.getProperties()).append(":").append(propVal);
    }
}
