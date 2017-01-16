package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/14
 */
public class SyncLook {

    private static class Service {
        private boolean isRunning = true;//volatile

        public void runMethod() {
            /**
             * https://www.zhihu.com/question/39458585/answer/81521474
             *
             * while 循环会被JIT编译器优化
             *
             * 读取isRunning变量的操作提示到循环外
             * boolean hoistedIsRunning = isRunning;
             *
             * while(hoistedIsRunning) {}
             *
             */
            while (isRunning) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
