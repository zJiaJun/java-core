package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.Semaphore;

/**
 * Created by zhujiajun
 * 16/2/11 19:04
 *
 * Semaphore类似于——人流控制,如地铁早高峰,一次只允许进入100人,只有100人当中的(某个)上车或出站,才能允许下一批次100人(某个)进入
 */
public class SemaphoreExample {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3); //一次只允许3人访问
        for (int i = 0;i < 10;i++) {
            final int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("用户" + finalI + "连接上了");
                    Thread.sleep(300);
                    semaphore.acquire(); //获取许可
                    System.out.println("用户" + finalI + "开始访问后台程序");
                    Thread.sleep(500); //模仿用户访问后台过程
                    semaphore.release();//释放允许下一个线程访问进入后台
                    System.out.println("用户" + finalI + "访问结束。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
        System.out.println("Main thread end");
    }
}
