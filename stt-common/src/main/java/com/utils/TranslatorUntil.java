package com.utils;

import org.apache.http.entity.StringEntity;

import java.util.List;

public class TranslatorUntil {
    public static StringEntity getUTF8JSON(List<String> source) {
        StringBuffer json = new StringBuffer("[");
        if (!source.isEmpty()) {
            json.append("{\"Text\": \"" + source.get(0) + "\"}");
        }
        for (int i = 1; i < source.size(); i++) {
            json.append(", {\"Text\": \"" + source.get(i) + "\"}");
        }
        json.append("]");
        StringEntity entity = new StringEntity(json.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        return entity;
    }
}
