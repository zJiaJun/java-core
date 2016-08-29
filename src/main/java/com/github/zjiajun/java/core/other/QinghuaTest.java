package com.github.zjiajun.java.core.other;

/**
 * @author zhujiajun
 * @since 16/8/29
 */
public class QinghuaTest {

    static String s0,s1;

    public void test(Object object) {
        System.out.println("Object");
    }

    public void test(String string) {
        System.out.println("String");
    }


    public static void main(String[] args) {
        QinghuaTest test = new QinghuaTest();
        test.test(null);//String

        s0 = s0 + s1;
        System.out.println(s0);//nullnull

    }
}
