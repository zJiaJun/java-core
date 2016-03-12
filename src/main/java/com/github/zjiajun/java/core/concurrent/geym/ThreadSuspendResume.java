package com.github.zjiajun.java.core.concurrent.geym;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/3/12 12:39
 *
 * 线程挂起和继续执行错误例子,都不推荐使用@Deprecated
 *
 * suspend 不会释放锁,直到resume方法被执行
 * resume方法先于suspend执行,suspend再执行,则会出现类似"冻结"的状态,类似死锁
 */
public class ThreadSuspendResume {

    private static final Object object = new Object();

    public static class ThreadTest extends Thread {

        public ThreadTest(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            System.out.println(getName() + "准备获取锁");
            synchronized (object) {
                System.out.println("in: " + getName());
                Thread.currentThread().suspend();
                System.out.println("finish: " + getName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest t1 = new ThreadTest("t1");
        ThreadTest t2 = new ThreadTest("t2");

        t1.start();
        TimeUnit.SECONDS.sleep(2); //让主线程等待会,确保t1的suspend执行
        t2.start();//t2执行,这里获取不到锁,因为t1还处在suspend挂起中,资源没有释放,等t1的resume执行后,才能获取锁
        TimeUnit.SECONDS.sleep(3);

        t1.resume();
//        TimeUnit.SECONDS.sleep(1);//等待会,t1 resume后,t2获取锁,确保suspend方法执行,后续t2再resume
        t2.resume();//t2的resume先于t2的suspend执行,程序"死锁"或叫"冻结",但线程的状态仍是running
        t1.join();
        t2.join();

    }
}
