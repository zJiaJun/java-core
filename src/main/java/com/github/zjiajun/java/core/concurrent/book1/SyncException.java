package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/3
 */
public class SyncException {

    synchronized public void testMethod() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println("出现异常，自动释放锁");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SyncException syncException = new SyncException();
        for (int i = 0; i < 2; i++) {
            new Thread(syncException::testMethod).start();
        }
    }
}
