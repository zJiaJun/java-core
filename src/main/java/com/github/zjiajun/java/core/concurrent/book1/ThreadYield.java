package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2016/12/23
 */
public class ThreadYield {

    private void yiedld() {
        Thread thread = new Thread(() -> {
            long t1 = System.currentTimeMillis();
            int count = 0;
            for (int i = 0; i < 100_000_0; i++) {
                Thread.yield();//放弃当前的cpu资源，其他任务有机会获取cpu时间片执行任务
                count += count;
            }
            long t2 = System.currentTimeMillis();
            System.out.println("time : " + (t2 - t1));
        });
        thread.start();
    }

    public static void main(String[] args) {
        ThreadYield threadYield = new ThreadYield();
        threadYield.yiedld();

    }
}
