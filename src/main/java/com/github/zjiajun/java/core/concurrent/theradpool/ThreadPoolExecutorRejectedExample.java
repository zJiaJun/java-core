package com.github.zjiajun.java.core.concurrent.theradpool;

import java.util.concurrent.*;

/**
 * Created by zhujiajun
 * 16/5/22 13:32
 */
public class ThreadPoolExecutorRejectedExample {


    private void doWork(ExecutorService executorService) {
        for (int i = 0; i < 11; i++) {
            executorService.submit(() -> {
                System.out.println("Thread name :" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //当拒绝策略为默认的AbortPolicy不会执行,因为抛出异常,除非try catch
        executorService.shutdown();
    }

    /**
     * 默认任务拒绝策略,丢弃任务并抛出异常RejectedExecutionException
     */
    public void abortPolicy() {
        final ExecutorService executorService = new ThreadPoolExecutor(3,5,
                0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        doWork(executorService);
    }

    /**
     * 由调用线程处理拒绝的任务
     */
    public void callerRunsPolicy() {
        final ExecutorService executorService = new ThreadPoolExecutor(3,5,
                0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5),new ThreadPoolExecutor.CallerRunsPolicy());
        doWork(executorService);
    }

    /**
     * 丢弃任务,不抛出异常,什么都不做
     */
    public void discardPolicy() {
        final ExecutorService executorService = new ThreadPoolExecutor(3,5,
                0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5),new ThreadPoolExecutor.DiscardPolicy());
        //只会执行10个任务
        doWork(executorService);
    }

    /**
     * 丢弃队列最前面的任务,然后重新尝试执行任务
     */
    public void discardOldestPolicy() {
        final ExecutorService executorService = new ThreadPoolExecutor(3,5,
                0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5),new ThreadPoolExecutor.DiscardOldestPolicy());
        doWork(executorService);
    }

    public static void main(String[] args) {
        ThreadPoolExecutorRejectedExample executorExample = new ThreadPoolExecutorRejectedExample();
        executorExample.callerRunsPolicy();
        executorExample.discardPolicy();
        executorExample.discardOldestPolicy();
        executorExample.abortPolicy();

    }
}
