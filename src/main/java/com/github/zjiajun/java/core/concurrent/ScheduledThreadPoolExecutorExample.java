package com.github.zjiajun.java.core.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhujiajun
 * 16/4/11 22:03
 *
 * 任务执行线程池简单例子
 */
public class ScheduledThreadPoolExecutorExample {


    static class ScheduledThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public ScheduledThreadFactory() {}

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r,"scheduled-thread-" + threadNumber.getAndIncrement());
            if (thread.isDaemon()) thread.setDaemon(false);
            if (thread.getPriority() != Thread.NORM_PRIORITY) thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }

    private final ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(10,new ScheduledThreadFactory());

    public void executeByPeriod(Runnable cmd) {
        scheduled.scheduleAtFixedRate(cmd, 1, 3, TimeUnit.SECONDS);//延迟1秒,每隔3秒执行
    }


    public void shutdown() {
        scheduled.shutdown();
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutorExample executorExample = new ScheduledThreadPoolExecutorExample();
        System.out.println("Begin scheduled " + new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
        executorExample.executeByPeriod(new Work(" Work1",true));
        executorExample.executeByPeriod(new Work(" Work2",false));
    }

    static class Work extends TimerTask {

        private volatile boolean flag;
        private String workName;

        public Work(String name,boolean flag) {
            workName = name;
            this.flag = flag;
        }

        @Override
        public void run() {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
            System.out.println(format.format(new Date()) + workName + " Thread Name: " + Thread.currentThread().getName());
            //do something
            try {
                if (flag) TimeUnit.SECONDS.sleep(5);//休眠5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(format.format(new Date()) + workName + " Finished");
        }
    }




}
