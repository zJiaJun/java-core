package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhujiajun
 * 16/1/18 17:32
 *
 * 线程间数据交换例子
 */
public class ExchangeExample {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            String a = "银行流水A";
            try {
                exchanger.exchange(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            String b = "银行流水B";
            try {
                String a = exchanger.exchange(b);
                System.out.println("A和B数据是否一致：" + a.equals(b) + ",A录入的是：" + a + ",B录入是：" + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.shutdown();

    }
}
