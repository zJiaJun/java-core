package com.github.zjiajun.java.core.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/2/11 21:49
 *
 * 使用wait和notify实现简单的生产消费阻塞的例子
 */
public class WaitNotifyExample {

    private final int size;//容器大小,相当于生产物品仓库的容量

    private final List<String> storage; //存放生产的容器

    public WaitNotifyExample(int size, List<String> storage) {
        this.size = size;
        this.storage = storage;
    }

    public void produce(String data)  {
        System.out.println("准备生产数据: " + data);
        synchronized (storage) {
            while (storage.size() == size) {
                System.out.println("仓库满啦,先消费点把");
                try {
                    storage.wait(); //仓库满了,等待消费
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("可以生产数据: " + data);
            storage.notify(); //通知可以消费了
            storage.add(data);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String consume() {
        System.out.println("准备消费数据");
        synchronized (storage) {
            while (storage.size() == 0) {
                System.out.println("仓库空啊,先生产点把");
                try {
                    storage.wait(); //没有物品,等待生产
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("可以消费数据");
            storage.notify(); //通知可以生产了
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return storage.remove(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyExample example = new WaitNotifyExample(5,new ArrayList<>());
        Thread t1 = new Thread(() -> {
            for (;;)
                example.produce(new Date().toString());
        });
        Thread t2 = new Thread(() -> {
            for (;;) {
                String v = example.consume();
                System.out.println(v);
            }
        });
        t1.start();
        t2.start();

    }

}
