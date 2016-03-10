package com.github.zjiajun.java.core.concurrent.geym;

/**
 * Created by zhujiajun
 * 16/3/9 21:03
 */
public class AccountingSyncError implements Runnable {

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
//        AccountingSyncError accountingSyncError = new AccountingSyncError();
        Thread t1 = new Thread(new AccountingSyncError()); //error
        Thread t2 = new Thread(new AccountingSyncError()); //error
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(j);
    }
}
