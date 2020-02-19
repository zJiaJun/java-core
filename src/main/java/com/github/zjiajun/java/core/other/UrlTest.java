package com.github.zjiajun.java.core.other;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujiajun
 * 16/4/22 21:27
 *
 * get参数转换为map
 */
public class UrlTest {

    public static void main(String[] args) throws URISyntaxException {
        URI uri = URI.create("http://wwww.google.com/test/path?a=1&b=2&c=3");
        String path = uri.getPath();
        System.out.println(path);
        String query = uri.getQuery();
        System.out.println(query);

        Map<String,String> map = new HashMap<>();
        if (query != null && query.length() > 0) {
            String [] split = query.split("&");
            for (String param: split) {
                String [] split1 = param.split("=");
                if (split1.length == 2) {
                    map.put(split1[0],split1[1]);
                }
            }
        }
        System.out.println(map);
    }
}
