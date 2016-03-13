package com.github.zjiajun.java.core.concurrent.geym;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/3/13 13:46
 *
 * 线程stop错误例子
 */
public class ThreadStop {

    public static class User {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static final User user = new User();

    public static class WriteUserThread extends Thread {

        public WriteUserThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            System.out.println("Begin write user: " + getName());
            synchronized (user) {
                System.out.println(user);
                user.id = 1;
                Thread.currentThread().stop();
                System.out.println("赋值user name");
                user.name = "小明";
                //在user对象中的name赋值前stop,数据不完整,stop会释放锁.
                //如果其他线程正在等待获取锁并且读user对象,则只会取到id的数据
            }
        }
    }

    public static class ReadUserThread extends Thread {

        public ReadUserThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            System.out.println("Begin read user:" + getName());
            synchronized (user) {
                System.out.println(user);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WriteUserThread writeUserThread = new WriteUserThread("writeUserThread");
        ReadUserThread readUserThread = new ReadUserThread("readUserThread");

        writeUserThread.start();
        readUserThread.start();
    }
}
