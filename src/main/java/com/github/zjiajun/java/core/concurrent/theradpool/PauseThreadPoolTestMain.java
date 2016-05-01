package com.github.zjiajun.java.core.concurrent.theradpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/2/28 19:13
 *
 * 暂停线程池main测试
 */
public class PauseThreadPoolTestMain {

    public static void main(String[] args) throws InterruptedException {
        List<Runnable> commands = new ArrayList<>();
        for (int i = 1;i <= 10;i++)
            commands.add(new Command("thread-" + i));

        PauseThreadPool pauseThreadPool = new PauseThreadPool(5,5,0,TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        commands.forEach(pauseThreadPool::submit);

        TimeUnit.SECONDS.sleep(3);
        pauseThreadPool.pause();

        TimeUnit.SECONDS.sleep(2);
        pauseThreadPool.resume();

        pauseThreadPool.shutdown();

    }

    static class Command implements Runnable {

        private String name;

        public Command(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "-command running...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
