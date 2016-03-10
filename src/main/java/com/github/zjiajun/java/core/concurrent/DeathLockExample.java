package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 15/9/19 14:31
 */
public class DeathLockExample {

    public static void main(String[] args) {

        final Object objA = new Object();
        final Object objB = new Object();

        Thread thread1 = new Thread(() -> {

            synchronized (objA) { //锁住A对象
                System.out.println(Thread.currentThread().getName() + "Lock A");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (objB) { //锁住B对象
                    System.out.println(Thread.currentThread().getName() + "Lock B");

                }

            }
        });

        Thread thread2 = new Thread(() -> {

            synchronized (objB) { //锁住B对象
                System.out.println(Thread.currentThread().getName() + "Lock B");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objA) { //锁住A对象
                    System.out.println(Thread.currentThread().getName() + "Lock A");

                }

            }
        });
        thread1.start();
        thread2.start();

    }

}
