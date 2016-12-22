package com.github.zjiajun.java.core.concurrent.book2.charpter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhujiajun
 * 16/1/6 20:53
 *
 *  显示锁Lock-ReentrantLock
 */
public class ReentrantLockDemo {

    class Count {

        final ReentrantLock lock = new ReentrantLock();

        public void get() {
            try {
                lock.lock();
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " get begin");
                Thread.sleep(1000);
                System.out.println(threadName + " get end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void put() {
            try {
                lock.lock();
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " put begin");
                Thread.sleep(1000);
                System.out.println(threadName + " put end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        final Count count = new ReentrantLockDemo().new Count();
        for (int i = 0;i < 2;i++)
           new Thread(count::get, "threadGet_" + i).start();

        for (int i = 0;i < 2;i++)
            new Thread(count::put,"threadPut_" + i).start();
    }
}
