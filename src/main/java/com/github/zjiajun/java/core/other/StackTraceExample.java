package com.github.zjiajun.java.core.other;

/**
 * @author zhujiajun
 * @since 2016/10/9
 */
public class StackTraceExample {

    private void methodA() {
        methodB();
    }

    private void methodB() {
        methodC();
    }

    private void methodC() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stackTrace) {
            System.out.println("MethodName: " + ste.getMethodName() + " LineNumber: " + ste.getLineNumber() + "\t" + ste);
        }
    }

    public static void main(String[] args) {
        StackTraceExample example = new StackTraceExample();
        example.methodA();
    }
}
