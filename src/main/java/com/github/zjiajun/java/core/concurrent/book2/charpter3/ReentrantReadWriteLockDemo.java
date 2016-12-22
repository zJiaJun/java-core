package com.github.zjiajun.java.core.concurrent.book2.charpter3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhujiajun
 * 16/1/7 20:52
 *
 * 显示锁ReadWriteLock-ReentrantReadWriteLock
 * 合适读多,写少的场景
 * 1.读-读 不互斥,可以并发读,不会阻塞
 * 2.读-写 互斥,有写线程的时候,读线程阻塞,反过了,读线程在使用,写线程阻塞
 * 3.写-写 互斥,写线程都是互斥的,如果有两个线程去写,A线程先拿到锁就先写,B线程就阻塞直到A线程释放锁
 */
public class ReentrantReadWriteLockDemo {

    class Count {

        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        final Lock readLock = readWriteLock.readLock();     //得到一个可被多个读操作共用的读锁,但它会排斥所有写操作
        final Lock writeLock = readWriteLock.writeLock();   //得到一个写锁,它会排斥所有其他的读操作和写操作

        public void get() {
            readLock.lock();
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " read begin");
                Thread.sleep(1000);
                System.out.println(threadName + " read end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }

        public void put() {
            writeLock.lock();
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " write begin");
                Thread.sleep(1000);
                System.out.println(threadName + " write end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final Count count = new ReentrantReadWriteLockDemo().new Count();
        for (int i = 0; i < 2; i++)
            new Thread(count::get,"threadRead_" + i).start();

        for (int i = 0; i < 2; i++)
            new Thread(count::put,"threadWrite_" + i).start();
    }
}
