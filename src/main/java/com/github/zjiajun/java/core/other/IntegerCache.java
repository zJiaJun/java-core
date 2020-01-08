package com.github.zjiajun.java.core.other;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/1/8 23:50
 */
public class IntegerCache {

    public static void main(String[] args) {
        Integer n1 = -127;
        Integer n2 = -127;

        System.out.println(n1 == n2);

        Integer n3 = 127;
        Integer n4 = 127;
        System.out.println(n3 == n4);

        Integer n5 = 128;
        Integer n6 = 128;
        System.out.println(n5 == n6);

        /**
         * -Djava.lang.Integer.IntegerCache.high=1000
         */
        Integer n7 = 1000;
        Integer n8 = 1000;
        System.out.println(n7 == n8);
    }
}
