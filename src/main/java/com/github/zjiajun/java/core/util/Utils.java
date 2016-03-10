package com.github.zjiajun.java.core.util;

/**
 * Created by zhujiajun
 * 15/12/24 22:11
 */
public class Utils {

    public static Thread getCurrentThread() {
        return Thread.currentThread();
    }

    public static String getCurrentThreadName() {
        return getCurrentThread().getName();
    }

    public static long getCurrentThreadId() {
        return getCurrentThread().getId();
    }
}
