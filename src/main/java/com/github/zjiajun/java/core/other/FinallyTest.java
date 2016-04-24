package com.github.zjiajun.java.core.other;

/**
 * Created by zhujiajun
 * 16/4/24 22:11
 */
public class FinallyTest {

    public static void main(String[] args) {
        FinallyTest test = new FinallyTest();
        System.out.println(test.test());
    }

    public String test() {
        String s = "";
        try {
            s = "try";
            System.out.println(s);
            return s;
        } catch (Exception e) {
            s = "exception";
            System.out.println(s);
            return s;
        } finally {
            System.out.println(s);
            s = "finally";
            System.out.println(s);
        }
    }
}
