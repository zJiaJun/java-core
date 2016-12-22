package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2016/12/22
 */
public class ThreadSuspend {

    private void testSuspend() throws InterruptedException {
        Thread thread = new Thread(() -> {
           while (true) {
               int i = 0;
               System.out.println(i);
           }
        });
        thread.start();
        Thread.sleep(1000);
        thread.suspend();
        //不会输出，因为运行到println停止时，同步锁未释放。println方法一直处于"暂停"状态,且锁未被释放
        System.out.println("end");
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadSuspend threadSuspend = new ThreadSuspend();
        threadSuspend.testSuspend();
    }
}
