package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/12
 */
public class VolatileWhile implements Runnable {

    private volatile boolean isRunning = true;

    @Override
    public void run() {
        System.out.println("begin run");
        while (isRunning) {
            System.out.println("running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("end run");

    }

    public static void main(String[] args) throws InterruptedException {
        VolatileWhile volatileWhile = new VolatileWhile();
        Thread t1 = new Thread(volatileWhile);
        t1.start();
        System.out.println("stop thread");
        Thread.sleep(3000);
        volatileWhile.isRunning = false;

    }
}
