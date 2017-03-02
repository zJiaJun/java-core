package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/3/1
 */
public class OneByOneRun {

    private volatile boolean prevIsA = false;

    private synchronized void backupA() {
        try {
            while (prevIsA) {
                wait();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println("******");
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void backupB() {
        try {
            while (!prevIsA) {
                wait();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println("$$$$$$");
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        OneByOneRun oneByOneRun = new OneByOneRun();
        for (int i = 0; i < 2; i++) {
            Thread threadB = new Thread(oneByOneRun::backupB);
            threadB.start();
            Thread threadA = new Thread(oneByOneRun::backupA);
            threadA.start();
        }
    }
}
