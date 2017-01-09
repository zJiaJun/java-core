package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/9
 */
public class StaticSync {

    private static class StaticService {

        synchronized public static void methodA() {
            System.out.println(Thread.currentThread().getName() + "---" + "methodA---" + System.currentTimeMillis() + "---begin");
            System.out.println(Thread.currentThread().getName() + "---" + "methodA---" + System.currentTimeMillis() + "---end");
        }

        synchronized public static void methodB() {
            System.out.println(Thread.currentThread().getName() + "---" + "methodB---" + System.currentTimeMillis() + "---begin");
            System.out.println(Thread.currentThread().getName() + "---" + "methodB---" + System.currentTimeMillis() + "---end");
        }
    }

    public static void main(String[] args) {
        StaticService s1 = new StaticService();
        StaticService s2 = new StaticService();
        Thread t1 = new Thread(() -> s1.methodA());
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(() -> s2.methodB());
        t2.setName("t2");
        t2.start();
    }
}
