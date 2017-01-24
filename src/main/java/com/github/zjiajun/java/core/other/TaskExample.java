package com.github.zjiajun.java.core.other;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhujiajun
 * @since 2017/1/24
 */
public class TaskExample {

    private static final int TASK_TIME_INTERVAL = 3 * 60 * 1000; //3min

    private static final int TASK_SLEEP_SEC =  20 * 1000; //20s

    private static final AtomicLong ATOMIC_COUNT = new AtomicLong(0);

    public static void main(String[] args) {
        System.out.println("begin task...");
        long lastTaskTime = 0;
        do {
            long increment = ATOMIC_COUNT.getAndIncrement();
            System.out.println("count: " + increment);
            try {
                TimeUnit.MILLISECONDS.sleep(TASK_SLEEP_SEC);
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastTaskTime < TASK_TIME_INTERVAL) {
                    continue;
                }
                //doWork
                System.out.println("do work...");
                TimeUnit.MILLISECONDS.sleep(TASK_SLEEP_SEC);
                lastTaskTime = currentTime;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }
}
