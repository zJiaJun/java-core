package com.github.zjiajun.java.core.jvm;


/**
 * Created by zhujiajun
 * 16/6/7 11:27
 */
public class OutOfMemoryErrorExample {


    /**
     * -Xms10m
     * -Xmx10m
     * -Xmn4m
     *
     * -XX:+PrintGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -XX:+PrintGCTimeStamps
     *
     * @param args args
     */
    public static void main(String[] args) throws InterruptedException {
        new MemoryObject(6 * 1024 * 1024);

    }
}
