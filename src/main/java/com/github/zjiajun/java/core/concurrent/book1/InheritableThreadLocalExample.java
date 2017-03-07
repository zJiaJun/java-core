package com.github.zjiajun.java.core.concurrent.book1;

import com.github.zjiajun.java.core.util.typedef.U;

/**
 * @author zhujiajun
 * @since 2017/3/7
 */
public class InheritableThreadLocalExample {

    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "initValue";
        }

        @Override
        protected String childValue(String parentValue) {
            return "父线程值:[" + parentValue + "],子线程修改的值[child]";
        }
    };

    public static void main(String[] args) {
        threadLocal.set("123");
        System.out.println(U.currentThread() + "-" + threadLocal.get());

        new Thread(() -> System.out.println(U.currentThread() + "-" + threadLocal.get()),"sun").start();

    }
}
