package com.github.zjiajun.java.core.other;

/**
 * @author zhujiajun
 * @since 16/8/29
 *
 * 清华大学一个java考试题目
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

        System.out.println(s1 == null);
        System.out.println(s0 == null);
        System.out.println(s0.equals("nullnull"));
    }
}
