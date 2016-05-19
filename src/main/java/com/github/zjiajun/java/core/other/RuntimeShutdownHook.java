package com.github.zjiajun.java.core.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/5/18 22:05
 */
public class RuntimeShutdownHook {


    final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void doWork() {
        for (int i = 0; i < 5;i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Work finished");
                }
            });
        }
    }

    public void register() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutdown hook");
            }
        });
        System.out.println("Register shutdown hook");
    }

    public static void main(String[] args) throws InterruptedException {
        RuntimeShutdownHook hook = new RuntimeShutdownHook();
        hook.doWork();
        hook.register();
        System.out.println("Last program line");
        hook.executorService.shutdown();//线程池关闭才会触发shutdownHook
        hook.executorService.awaitTermination(1,TimeUnit.SECONDS);

    }
}
