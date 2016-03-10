package com.github.zjiajun.java.core.concurrent.entry2master.charpter2;

/**
 * Created by zhujiajun
 * 16/1/4 20:37
 *
 * 守护线程例子:前台线程(非守护线程)是保证执行完毕的,后台线程还没有执行完毕就退出了
 *
 * JRE判断程序是否执行结束的标准是所有的前台线程执行完毕了,而不管后台线程的状态
 */
public class ThreadDaemonDemo {

    public static void main(String[] args) {
        ThreadDaemonDemo threadDaemonDemo = new ThreadDaemonDemo();
        ThreadA tA = threadDaemonDemo.new ThreadA();
        ThreadB tB = threadDaemonDemo.new ThreadB();

        tA.setDaemon(true);//该方法必须在启动线程前调用
        tA.start();
        tB.start();
        Thread mainThread = Thread.currentThread();
        System.out.println("线程A是不是守护线程" + tA.isDaemon());
        System.out.println("线程B是不是守护线程" + tB.isDaemon());
        System.out.println("线程main是不是守护线程" + mainThread.isDaemon());
    }

    class ThreadB extends Thread {

        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("线程B第" + i + "次执行!");
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadA extends Thread {

        public void run() {
            for (long i = 0; i < 999999L; i++) {
                System.out.println("后台线程A第" + i + "次执行!");
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
