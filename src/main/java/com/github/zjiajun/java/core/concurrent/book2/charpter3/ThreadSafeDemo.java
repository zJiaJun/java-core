package com.github.zjiajun.java.core.concurrent.book2.charpter3;

/**
 * Created by zhujiajun
 * 16/1/5 19:51
 *
 * 线程安全例子
 */
public class ThreadSafeDemo {

    public int num = 0;

    public void add() { //线程不安全
        common();
    }

    public synchronized void syncMethodAdd() { //使用同步方法,线程安全
        common();
    }

    public void syncCodeAdd() { //使用同步代码快,线程安全
        synchronized (this) {
            common();
        }
    }

    private void common() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num += 1;
        System.out.println(Thread.currentThread().getName() + "-" + num);
    }

    class ThreadA extends Thread {

        private ThreadSafeDemo threadSafeDemo;

        public ThreadA(ThreadSafeDemo threadNoSafe) {
            this.threadSafeDemo = threadNoSafe;
        }

        public void run() {
//            threadSafeDemo.add();
//            threadSafeDemo.syncMethodAdd();
            threadSafeDemo.syncCodeAdd();
        }
    }

    public static void main(String[] args) {
        ThreadSafeDemo threadSafeDemo = new ThreadSafeDemo();
        for (int i = 0; i < 5; i++) {
            ThreadA threadA = threadSafeDemo.new ThreadA(threadSafeDemo);
            threadA.start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5个人干完活:最后的值是" + threadSafeDemo.num);
    }
}
