package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhujiajun
 * @since 2016/12/13
 *
 * LockSupport简单例子
 */
public class LockSupportExample {

    public static void main(String[] args) throws InterruptedException {
        Thread parkThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " begin park");
            LockSupport.park();
        });
        parkThread.start();

        System.out.println("waiting 3s...");
        TimeUnit.SECONDS.sleep(3);

        System.out.println(Thread.currentThread().getName() + " unpark :" + parkThread.getName());
        LockSupport.unpark(parkThread);
    }
}
