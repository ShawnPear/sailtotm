package com.utils;

import org.apache.http.client.utils.URIBuilder;

import java.util.Map;

public class NetUtil {
    public static String getFullPath(String scheme, String host, String path, Map<String, Object> query) {
        try {
            URIBuilder uri = new URIBuilder();
            uri.setScheme(scheme)
                    .setHost(host)
                    .setPath(path);
            for (Map.Entry<String, Object> me : query.entrySet()) {
                if(me.getValue() == null){
                    uri.setParameter(me.getKey(), "");
                }else {
                    uri.setParameter(me.getKey(), me.getValue().toString());
                }
            }
            return uri.build().toString();
        }catch (Exception e){
            return "";
        }
    }
}
