package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/14
 */
public class SyncLook {

    private static class Service {
        private boolean isRunning = true;

        public void runMethod() {
            while (isRunning) {}
            System.out.println("Stop!!!");
        }

        public void stop() {
            isRunning = false;
        }
    }

    private static class ThreadA extends Thread {

        private Service service;

        public ThreadA(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.runMethod();
        }
    }

    private static class ThreadB extends Thread {

        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.stop();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        ThreadA t1 = new ThreadA(service);
        t1.start();
        Thread.sleep(5000);
        ThreadB t2 = new ThreadB(service);
        t2.start();
        System.out.println("send stop...");
    }
}
