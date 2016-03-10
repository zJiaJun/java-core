package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhujiajun
 * 16/1/8 22:09
 *
 * 两个线程,A线程输出123,789;B线程输出456
 */
public class LockConditionExample2 {

    private final ReentrantLock lock;
    private Condition reachThreeCondition;
    private Condition reachSixCondition;

    private int number = 1;

    public LockConditionExample2() {
        lock = new ReentrantLock();
        reachThreeCondition = lock.newCondition();
        reachSixCondition = lock.newCondition();
    }

    /**
     * A线程——先输出123,释放信号给B,等待B线程输出456,得到通知,继续输出789
     */
    public void out3Reach6Out9() {
        new Thread(() -> {
            String threadA = Thread.currentThread().getName();
            lock.lock();
            try {
                System.out.println(threadA + " 123 start");
                //A线程先输出前3个数
                while (number <= 3) {
                    System.out.println(threadA + ":" + number);
                    number++;
                }
                //输出到3时要signal，告诉B线程可以开始了
                reachThreeCondition.signal();
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                reachSixCondition.await();  //等待输出6的条件
                System.out.println(threadA + " 789 start");
                while (number <= 9) { //输出剩余数字
                    System.out.println(threadA + ":" + number);
                    number++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"Thread-A").start();


    }

    /**
     * B线程-等待A线程输出123,得到通知,输出456,释放信号给A
     */
    public void reach3Out6() {
        new Thread(() -> {
            String threadB = Thread.currentThread().getName();
            lock.lock();
            try {
                while (number <= 3) {
                    //等待3输出完毕的信号
                    reachThreeCondition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                //已经收到信号，开始输出4,5,6
                System.out.println(threadB + " 456 start");
                while (number <= 6) {
                    System.out.println(threadB + ":" + number);
                    number++;
                }
                //4,5,6输出完毕，告诉A线程6输出完了
                reachSixCondition.signal();
            } finally {
                lock.unlock();
            }

        },"Thread-B").start();
    }

    public static void main(String[] args) {
        LockConditionExample2 conditionExample2 = new LockConditionExample2();
        conditionExample2.out3Reach6Out9();
        conditionExample2.reach3Out6();
    }
}
