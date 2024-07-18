package com.yasyl.sailtotm.utils;

import lombok.Builder;
import lombok.Data;
import org.apache.http.client.utils.URIBuilder;

import java.util.Map;

@Data
@Builder
public class GetUri {
    private String scheme;
    private String host;
    private String path;
    private Map<String, Object> query;

    @Override
    public String toString() {
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
