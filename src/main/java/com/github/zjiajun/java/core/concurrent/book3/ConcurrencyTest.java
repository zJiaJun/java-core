package com.github.zjiajun.java.core.concurrent.book3;

/**
 * @author zhujiajun
 * @since 2017/6/14
 *
 * 并发执行未必比串行快,因为有线程创建,上下文切换的开销
 */
public class ConcurrencyTest {

    private static final long count = 1000000000L;

    public static void main(String[] args) throws InterruptedException {
        //并发执行
        concurrency();
        //串行执行
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a +=5;
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms, b=" + b);

    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial :" + time + "ms, b=" + b + ", a=" + a);
    }
}
