package com.github.zjiajun.java.core.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhujiajun
 * 16/5/20 10:04
 */
public class SimpleVMArg {


    /**
     * VM agrs:
     *
     * -Xms200M
     * -Xmx200M
     * -Xmn100M
     * -XX:+UseSerialGC
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1
     *
     * -XX:+PrintGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -XX:+PrintGCTimeStamps
     * -XX:+PrintTenuringDistribution
     * @param args args
     */
    public static void main(String[] args) throws InterruptedException {
        demo();
//        new CountDownLatch(1).await();
    }

    private static void demo() {
        final int tenMB = 10 * 1024 * 1024; //10MB
        byte [] alloc1,alloc2,alloc3;

        alloc1 = new byte[tenMB / 5];//2M
        alloc2 = new byte[tenMB * 5];//50M
        alloc3 = new byte[tenMB * 4];//触发GC
        alloc3 = null;
        alloc3 = new byte[tenMB * 6];//触发GC

        /*
        Young 80+10+10 = 100
        Old 100
         */

    }
}
