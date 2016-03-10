package com.github.zjiajun.java.core.concurrent.geym;

/**
 * Created by zhujiajun
 * 16/3/9 20:57
 */
public class AccountingSync2 implements Runnable {

    private static AccountingSync2 instance = new AccountingSync2();
    private static int j = 0;

    public synchronized void increase() {
        j++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(j);
    }


}
