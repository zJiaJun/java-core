package com.github.zjiajun.java.core.other;

/**
 * Created by zhujiajun
 * 16/3/22 13:40
 */
public class ClassCanonicalName {

    public static void main(String[] args) {
        String canonicalName = String.class.getCanonicalName();
        System.out.println(canonicalName);
    }
}
