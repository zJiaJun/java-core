package com.github.zjiajun.java.core.concurrent.book2.charpter5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/1/31 11:09
 */
public class ArrayBlockingQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10,false);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                arrayBlockingQueue.put("value");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        String take = arrayBlockingQueue.take();
        System.out.println(take);
    }
}
