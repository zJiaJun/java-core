package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/12
 */
public class VolatileWhile implements Runnable {

    private boolean isRunning = true;////volatile

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        System.out.println("begin run");
        while (isRunning) {

        }
        System.out.println("end run");

    }

    public static void main(String[] args) throws InterruptedException {
        VolatileWhile volatileWhile = new VolatileWhile();
        Thread t1 = new Thread(volatileWhile);
        t1.start();
        System.out.println("stop thread");
        Thread.sleep(3000);
        volatileWhile.stop();
    }
}
