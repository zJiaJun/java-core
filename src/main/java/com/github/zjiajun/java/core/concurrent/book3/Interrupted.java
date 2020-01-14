package com.github.zjiajun.java.core.concurrent.book3;

import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/9/25
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    e.printStackTrace();
                }
            }
        }, "sleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {

            }
        },"busyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();


        System.out.println(sleepThread.getName() + " is " + sleepThread.isInterrupted());
        System.out.println(busyThread.getName() + " is " + busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(10);
    }
}
