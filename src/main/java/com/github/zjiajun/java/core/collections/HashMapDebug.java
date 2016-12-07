package com.github.zjiajun.java.core.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2016/12/6
 */
public class HashMapDebug {

    private void put() {
        Map<HashKey,String> map = new HashMap<>();
           /*
         * HashMap 判断key的hashCode，如相等，再判断equals是否相等
         */
        HashKey k1 = new HashKey();
        map.put(k1,"1");
        HashKey k2 = new HashKey();
        map.put(k2,"2");
    }

    private void rehash() {
        Map<String,String> map = new HashMap<>();
        for (int i = 1;i <=13;i++) {
            map.put("key_"+i, "value_"+i);
            System.out.println(map.size());
        }
    }

    private void threshold() {
        Map<String,String> map = new HashMap<>(16,1.75f);
        //threshold = 16
    }

    private static class HashKey {
        @Override
        public int hashCode() {
            System.out.println("invoke : hashCode");
            return 21;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("invoke equals");
            return false;
        }
    }

    public static void main(String[] args) {
        HashMapDebug hashMapDebug = new HashMapDebug();
        hashMapDebug.put();
        hashMapDebug.rehash();
        hashMapDebug.threshold();

    }
}
