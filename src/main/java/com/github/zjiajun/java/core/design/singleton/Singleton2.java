package com.github.zjiajun.java.core.design.singleton;

/**
 * Created by zhujiajun
 * 16/1/25 14:01
 */
public class Singleton2 {

    private static final Singleton2 INSTANCE = new Singleton2();

    private Singleton2(){}

    public Singleton2 getInstance() {
        return INSTANCE;
    }
}
