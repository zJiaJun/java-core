package com.github.zjiajun.java.core.concurrent;

/**
 * Created by zhujiajun
 * 15/7/28 17:14
 */
public class ThreadCallBack {

    public static void main(String[] args) {
        System.out.println("Main ThreadId: " + Thread.currentThread().getId());
        final String [] str = new String[1];
        runThreadCallBack(new CallbackListener() {
            @Override
            public void onFinish(String s) {
                System.out.println("onFinish ThreadId: " + Thread.currentThread().getId());
                System.out.println(s);
                str[0] = s;
            }
            @Override
            public void onError(Exception e) {

            }
        });
        System.out.println(str[0]);//null
    }

    private static void runThreadCallBack(final CallbackListener callbackListener) {
        System.out.println("runThreadCallBack ThreadId: " + Thread.currentThread().getId());
        new Thread(() -> {
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                callbackListener.onError(e);
            }
            callbackListener.onFinish("CallbackListener");
            System.out.println("run ThreadId: " + Thread.currentThread().getId());
        }).start();

    }

    private interface CallbackListener{

        void onFinish(String s);

        void onError(Exception e);
    }
}
