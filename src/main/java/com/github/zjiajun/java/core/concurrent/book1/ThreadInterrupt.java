package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2016/12/22
 */
public class ThreadInterrupt {

    private void errorInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100_00; i++) {
                if (i % 1000 == 0)
                    System.out.println("i = " + (i + 1));
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("Error是否停止1 = " + thread.interrupted());
        System.out.println("Error是否停止2 = " + thread.interrupted());
        //thread.interrupted() 测试当前线程是否中断，而当前线程是main，从未中断过，返回false
    }

    private void rightInterrupted() {
        Thread.currentThread().interrupt();
        //清除中断状态，第二次为false，除非再第二次调用前又中断interrupt
        System.out.println("Right是否停止1 = " + Thread.interrupted());
        System.out.println("Right是否停止2 = " + Thread.interrupted());
    }

    private void isInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000; i++) {
                    System.out.println("i = " + (i + 1));
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("isInterrupted是否停止1 = " + thread.isInterrupted());
        System.out.println("isInterrupted是否停止2 = " + thread.isInterrupted());
    }


    private void interruptedFor() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100_00; i++) {
                if (Thread.interrupted()) {
                    System.out.println("已经是停止状态，我要推出了");
                    break;
                }
                System.out.println("i = " + (i + 1));
            }
            System.out.println("for 下面的语句");
        });
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }

    private void interruptedForException() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 100_00; i++) {
                    if (Thread.interrupted()) {
                        System.out.println("已经是停止状态，我要推出了");
                        throw new InterruptedException();
                    }
                    System.out.println("i = " + (i + 1));
                }
                System.out.println("for 下面的语句");
            } catch (InterruptedException e) {
                System.out.println("catch InterruptedException");
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }

    private void sleepInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("睡眠中停止线程: " + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }

    private void interruptedSleep() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("先停止，再遇到了sleep");
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();
    }

    private void returnInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("stop thread");
                    return;
                }
                System.out.println("timer= " + System.currentTimeMillis());
            }
        });
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadInterrupt threadInterrupt = new ThreadInterrupt();
//        threadInterrupt.errorInterrupted();
//        threadInterrupt.rightInterrupted();
//        threadInterrupt.isInterrupted();
//        threadInterrupt.interruptedFor();
//        threadInterrupt.interruptedForException();
//        threadInterrupt.sleepInterrupted();
//        threadInterrupt.interruptedSleep();
        threadInterrupt.returnInterrupted();
    }
}
