package com.github.zjiajun.java.core.design.singleton;

/**
 * Created by zhujiajun
 * 15/2/5 10:00
 */
public class Singleton {
    
    private Singleton() {}

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    

}
