package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhujiajun
 * 16/2/28 19:05
 *
 * 可暂停的线程池,通过重入锁的条件控制
 */
public class PauseThreadPool extends ThreadPoolExecutor {

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    private boolean isPause = false;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unPause = pauseLock.newCondition();

    public void pause() {
        try {
            pauseLock.lock();
            isPause = true;
            System.out.println("thread poll pause");
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume() {
        try {
            pauseLock.lock();
            isPause = false;
            unPause.signalAll();
            System.out.println("thread poll resume");
        } finally {
            pauseLock.unlock();
        }
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            if (isPause)
                unPause.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pauseLock.unlock();
        }
        System.out.println(t.getName() + "-begin");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        System.out.println(r.getClass().getName() + "-finished");
    }

    @Override
    protected void terminated() {
        super.terminated();
        System.out.println(getClass().getName() + "-terminated");
    }


}
