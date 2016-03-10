package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.*;

/**
 * Created by zhujiajun
 * 15/7/28 16:52
 */
public class ThreadReturnValue {

    public static void main(String[] args) {
        System.out.println("Main ThreadId: " + Thread.currentThread().getId());
        String content = runThreadJob();
        System.out.println(content);
    }

    private static String runThreadJob() {
        System.out.println("runThreadJob ThreadId: " + Thread.currentThread().getId());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(5*1000);//sleep 5s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Submit Callable ThreadId: " + Thread.currentThread().getId());
            return "Callback";
        });

        String content = null;
        try {
            content = future.get(); //wait 5s for Callable.call to finish
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return content;

    }


}
