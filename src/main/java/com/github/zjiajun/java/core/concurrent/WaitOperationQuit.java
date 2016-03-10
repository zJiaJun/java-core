package com.github.zjiajun.java.core.concurrent;

/**
 * Created by zhujiajun
 * 15/12/22 21:43
 *
 * wait操作无故退出
 */
public class WaitOperationQuit extends Thread {

    @Override
    public void run() {
        System.out.println(getName() + " Begin sleep");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " Finished sleep");
    }

    public static void main(String[] args) throws InterruptedException {
        WaitOperationQuit waitOperationQuit = new WaitOperationQuit();
        waitOperationQuit.start();

        /*
            现象:线程执行完毕后,wait操作就自动退出了.程序里并没有调用notify/notifyAll方法.
            解释:当线程结束的时候会调用notifyAll方法来让join方法返回.
            不推荐我们的应用在Thread实例上调用wait, notify, or notifyAll.

            It is recommended that
            applications not use {@code wait}, {@code notify}, or
            {@code notifyAll} on {@code Thread} instances.

            出自Thread类join方法api说明
        */
//        synchronized (waitOperationQuit) {
//            waitOperationQuit.wait();
//        }

        //wait操作作用在一个独立的对象上,而不是像前面那样作用于线程对象上.这时程序就会一直wait下去.
        Object obj = new Object();
        synchronized (obj) {
            obj.wait();
        }
        System.out.println("Finished");

    }

}
