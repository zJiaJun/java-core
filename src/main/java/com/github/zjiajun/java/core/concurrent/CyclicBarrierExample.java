package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhujiajun
 * 15/9/23 20:55
 */
public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,
            ()->System.out.println(Thread.currentThread().getName() + " CyclicBarrier init Runnable"));

    public void work1Start() {
        new Thread(this::barrierAwait).start();
    }

    public void work2Start() {
        new Thread(this::barrierAwait).start();
    }

    private void barrierAwait() {
        try {
            TimeUnit.SECONDS.sleep(2);
            cyclicBarrier.await(3, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " 全部线程到达屏障,继续执行");
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " 开始执行");
        CyclicBarrierExample cyclicBarrierExample = new CyclicBarrierExample();
        cyclicBarrierExample.work1Start();
        cyclicBarrierExample.work2Start();

        cyclicBarrierExample.work1Start();
        cyclicBarrierExample.work2Start();

        System.out.println(threadName + " 结束");


    }
}
