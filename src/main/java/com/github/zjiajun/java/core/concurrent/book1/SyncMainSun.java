package com.github.zjiajun.java.core.concurrent.book1;

/**
 * @author zhujiajun
 * @since 2017/1/3
 *
 * 当存在父子类继承关系时，子类可以通过可重入锁调用父类的同步方法
 */
public class SyncMainSun {

    private static class Main {
        protected int i = 10;

        synchronized public void opInMain() {
            i--;
            System.out.println("main op i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static class Sun extends Main {

        synchronized public void opInSun() {
            while (i > 0) {
                i--;
                System.out.println("sun op i =  " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.opInMain();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Sun sun = new Sun();
            sun.opInSun();
        });
        thread.start();
    }
}
