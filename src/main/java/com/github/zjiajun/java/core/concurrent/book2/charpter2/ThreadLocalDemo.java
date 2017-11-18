package com.github.zjiajun.java.core.concurrent.book2.charpter2;

import com.github.zjiajun.java.core.util.typedef.U;

/**
 * Created by zhujiajun
 * 15/12/24 22:04
 *
 * ThreadLocal例子
 */
public class ThreadLocalDemo {

    private static ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

    public ThreadLocal<Integer> getThreadLocal() {
        return seqNum;
    }

    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo threadLocalExample = new ThreadLocalDemo();
        TestClient t1 = new TestClient(threadLocalExample);
        TestClient t2 = new TestClient(threadLocalExample);
        TestClient t3 = new TestClient(threadLocalExample);

        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {

        private ThreadLocalDemo threadLocalExample;

        public TestClient(ThreadLocalDemo threadLocalExample) {
            this.threadLocalExample = threadLocalExample;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread : [" + U.currentThreadName() + "]" + " seqNum : [" + threadLocalExample.getNextNum() +"]");
            }
            threadLocalExample.getThreadLocal().remove();
        }
    }
}
