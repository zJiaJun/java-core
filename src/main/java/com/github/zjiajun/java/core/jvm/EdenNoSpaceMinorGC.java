package com.github.zjiajun.java.core.jvm;

/**
 * Created by zhujiajun
 * 16/6/7 10:54
 *
 * eden区没有空间,触发minorGC
 */
public class EdenNoSpaceMinorGC {

    /**
     * -Xms40m
     * -Xmx40m
     * -Xmn16m
     * -XX:+UseParallelGC
     *
     * -XX:+PrintGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCDateStamps
     * -XX:+PrintGCTimeStamps
     * @param args args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        MemoryObject memoryObject = new MemoryObject(1024 * 1024);
        for (int i = 0; i < 2; i++) {
            happenMinorGC(11);
            Thread.sleep(2000);
        }
    }
    private static void happenMinorGC(int index) throws InterruptedException {
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                Thread.sleep(2000);
                System.out.println("minorGC should happen");
            }
            new MemoryObject(1024 * 1024);
        }

    }
}
