package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/11
 *
 *  String s1 = "A";
 *  String s2 = "A";
 *  s1==s2 true
 *  因为有字符串常量池
 *
 *  加锁的时候是一个对象，另外个线程会阻塞
 */
public class StringAndSync {

    private void stringSync(String str) {
        synchronized (str) {
            while (true) {
                System.out.println(Thread.currentThread().getId());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        StringAndSync stringAndSync = new StringAndSync();
        new Thread(() -> stringAndSync.stringSync("A")).start();
        new Thread(() -> stringAndSync.stringSync("A")).start();

    }
}
