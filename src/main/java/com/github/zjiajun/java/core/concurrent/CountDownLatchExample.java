package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 15/9/23 20:29
 */
public class CountDownLatchExample {

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    private void doWork() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " countDown执行结束,执行后续代码");
    }
    public void work1Start() {
        new Thread(this::doWork).start();
    }

    public void work2Start() {
        new Thread(this::doWork).start();
    }

    public void anotherThread() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " just wait");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " just wait finish");
        }).start();

    }

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " 开始执行");
        CountDownLatchExample latchExample = new CountDownLatchExample();
        latchExample.work1Start();
        latchExample.work2Start();

        latchExample.anotherThread();
        countDownLatch.countDown();

        try {
            countDownLatch.await(10,TimeUnit.SECONDS); //等待10秒
            System.out.println(threadName + "等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
