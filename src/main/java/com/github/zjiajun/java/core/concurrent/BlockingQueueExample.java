package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhujiajun
 * 16/2/29 21:19
 */
public class BlockingQueueExample {

    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(() -> {
            for (int i = 0;i < 5;i++) {
                try {
                    business.sub(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0;i < 5;i++) {
            try {
                business.main(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Business {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);

        {
            try {
                queue2.put(1);//保证queue2阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void main(int i) throws InterruptedException {
            queue1.put(1);
            for (int j = 0;j < 10;j++)
                System.out.println("main thread is looping of "+j +" in " + i);
            queue2.take();
        }

        public void sub(int i) throws InterruptedException {
            queue2.put(1);//等主线程执行的main(i) take完,才能执行
            for (int j = 0;j < 10;j++)
                System.out.println("sub thread is looping of "+j +" in " + i);
            queue1.take();

        }

    }
}
