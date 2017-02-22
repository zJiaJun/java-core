package com.github.zjiajun.java.core.util;

/**
 * Created by zhujiajun
 * 15/12/24 22:11
 */
public class Utils {

    public static Thread currentThread() {
        return Thread.currentThread();
    }

    public static String currentThreadName() {
        return currentThread().getName();
    }

    public static long currentThreadId() {
        return currentThread().getId();
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
