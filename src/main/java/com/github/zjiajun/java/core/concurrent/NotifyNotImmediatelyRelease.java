package com.github.zjiajun.java.core.concurrent;


import java.util.concurrent.TimeUnit;

import static com.github.zjiajun.java.core.util.typedef.U.*;

/**
 * @author zhujiajun
 * @since 2017/2/22
 */
public class NotifyNotImmediatelyRelease {

    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(currentThreadName() + " begin waiting " + currentTimeMillis());
                try {
                    lock.wait();//进入阻塞队列，等待被唤醒，同时释放锁。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(currentThreadName() + " end waiting " + currentTimeMillis());
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        lock.notify();
                        //notify后不会立马通知，而是要等后续代码执行完毕，wait才会唤醒，进入就绪队列，等待cpu调度。
                        System.out.println(currentThreadName() + " notify " + currentTimeMillis());
                    }
                    System.out.println(currentThreadName() + "---" + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
