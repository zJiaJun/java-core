package com.github.zjiajun.java.core.concurrent.entry2master.charpter2;

/**
 * Created by zhujiajun
 * 16/1/1 21:00
 *
 * 线程中断例子:中断是一种协作机制,也就是通过中断并不能直接终止另一个线程,而需要被中断的线程自己处理中断
 */
public class ThreadInterruptDemo implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadInterruptDemo(),"Interrupt Thread");
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println("线程是否中断: " + thread.isInterrupted());
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {
            System.out.println("My Thread is running...");
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 1000) {
                // 让循环持续一段时间,减少打印次数
            }
            if (Thread.currentThread().isInterrupted()) {
                //需要线程本身去处理一下他的终止状态
                stop = true;
//                break;
            }
        }
        System.out.println("My Thread exiting under request...");
    }
}
