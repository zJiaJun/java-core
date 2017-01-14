package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/14
 */
public class SyncLook {

    private static class Service {
        private boolean isRunning = true;

        public void runMethod() {
            while (isRunning) {

            }
            System.out.println("Stop!!!");
        }

        public void stop() {
            isRunning = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Thread t1 = new Thread(service::runMethod);
        t1.start();
        Thread.sleep(5000);
        Thread t2 = new Thread(service::stop);
        t2.start();
        System.out.println("send stop...");
    }
}
