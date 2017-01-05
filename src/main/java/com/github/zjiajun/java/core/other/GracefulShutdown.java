package com.github.zjiajun.java.core.other;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/1/5
 *
 * on my MAC OS
 * use "kill -31 pid"
 */
public class GracefulShutdown {

    private static class ShutdownHook implements Runnable {

        @Override
        public void run() {
            System.out.println("ShutdownHook execute start...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ShutdownHook execute end...");
        }
    }


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Signal signal = new Signal("USR2");
        Signal.handle(signal, new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                System.out.println(signal);
                Thread t = new Thread(new ShutdownHook(),"shutdown-thread");
                Runtime.getRuntime().addShutdownHook(t);
                Runtime.getRuntime().exit(0);
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
