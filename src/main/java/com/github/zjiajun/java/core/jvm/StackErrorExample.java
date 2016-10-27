package com.github.zjiajun.java.core.jvm;

/**
 * @author zhujiajun
 * @since 2016/10/27
 */
public class StackErrorExample {

    /**
     * vmArgs -Xss1M -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps
     * @param args args
     */
    public static void main(String[] args) {
        StackErrorExample stackErrorExample = new StackErrorExample();
        stackErrorExample.unableCreateThread();
        stackErrorExample.stackOverflowError(0);
    }


    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
     */
    private void unableCreateThread() {
        int count = 0;
        try {
            for (int i = 0; i < 2500; i++) {
                new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                count++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.err.printf("Last Thread Num : %d \n",count);
        }
    }


    /**
     * Exception in thread "main" java.lang.StackOverflowError
     */
    private void stackOverflowError(long count) {
        if (count > 10000) System.err.println(count);
        count++;
        stackOverflowError(count);
    }
}
