package com.github.zjiajun.java.core.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2016/12/6
 */
public class HashMapDebug {

    private static class HashKey {
        @Override
        public int hashCode() {
            System.out.println("invoke : hashCode");
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("invoke equals");
            return false;
        }
    }

    public static void main(String[] args) {
        Map<HashKey,String> map = new HashMap<>();
        /*
         * HashMap 判断key的hashCode，如相等，再判断equals是否相等
         */
        map.put(new HashKey(),"1");
        map.put(new HashKey(),"2");


    }
}
