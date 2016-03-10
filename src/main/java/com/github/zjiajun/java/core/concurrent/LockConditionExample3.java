package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhujiajun
 * 16/1/9 14:53
 *
 * 两个线程交替输出1和2,即两个线程交替工作
 * A输出1,B输出2
 */
public class LockConditionExample3 {

    private final ReentrantLock lock;
    private Condition oneCondition;
    private Condition twoCondition;

    public LockConditionExample3() {
        lock = new ReentrantLock();
        oneCondition = lock.newCondition();
        twoCondition = lock.newCondition();
    }

    public void outOne() {
        new Thread(() -> {
            lock.lock();
            try {
                for (;;) {
                    System.out.println(1);      //输出1
                    TimeUnit.SECONDS.sleep(2);  //睡眠2秒减少打印
                    twoCondition.signal();      //通知线程B可以输出2
                    oneCondition.await();       //线程A等待,这里会执行释放锁操作,线程B就可以获得锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread-A").start();
    }

    public void outTwo() {
        new Thread(() -> {
            lock.lock();
            try {
                for(;;) {
                    twoCondition.await();       //等待,等待线程A的通知
                    System.out.println(2);      //输出2
                    TimeUnit.SECONDS.sleep(2);  //睡眠2秒减少打印
                    oneCondition.signal();      //通知线程A可以输出1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread-B").start();
    }

    public static void main(String[] args) throws InterruptedException {
        LockConditionExample3 conditionExample3 = new LockConditionExample3();
        conditionExample3.outTwo();
        conditionExample3.outOne();
    }
}
