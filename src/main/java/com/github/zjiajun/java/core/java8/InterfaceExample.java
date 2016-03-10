package com.github.zjiajun.java.core.java8;

/**
 * Created by zhujiajun
 * 16/1/14 20:58
 */
public class InterfaceExample {

    public static void main(String[] args) {
        InterfaceTestImpl testImpl = new InterfaceTestImpl();
        testImpl.foo();
        InterfaceTestImpl2 testImpl2 = new InterfaceTestImpl2();
        testImpl2.bar();

    }

    interface InterfaceTest {

        default void foo() {
            System.out.println("Calling foo");
        }
    }

    static class InterfaceTestImpl implements InterfaceTest {

    }

    interface InterfaceTest1 {
        default void bar() {
            System.out.println("Calling 1 bar");
        }
    }
    interface InterfaceTest2 {
        default void bar() {
            System.out.println("Calling 2 bar");
        }
    }

    //接口1和接口2都有默认实现bar方法,如果不重写bar方法,编译不通过
    static class InterfaceTestImpl2 implements InterfaceTest1,InterfaceTest2 {

        @Override
        public void bar() {
            InterfaceTest1.super.bar();//指定调用接口1的bar方法
        }
    }
}
