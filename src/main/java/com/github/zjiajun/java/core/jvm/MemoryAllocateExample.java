package com.github.zjiajun.java.core.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhujiajun
 * 16/7/5 20:33
 */
public class MemoryAllocateExample {


    /**
     * -Xms 300M
     * -Xmx 300M
     * -XX:NewRatio=4 young 和 old 的比值为1 : 4,young占heap的1/5
     *      ps: xmn =(xmx / (1+4)) = (300 / 5) = 60m
     * -XX:SurvivorRatio=4 2个survivor和eden比值为2:4,1个survivor占yong的1/6
     *      ps: from and to space = (young / (2+4)) = (60 / 6) = 10m
     * -XX:+PrintGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -XX:+PrintGCTimeStamps
     *
     * @param args args
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }
}
