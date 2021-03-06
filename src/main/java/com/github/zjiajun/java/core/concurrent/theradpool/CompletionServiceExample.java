package com.github.zjiajun.java.core.concurrent.theradpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhujiajun
 * @since 2017/2/21
 */
public class CompletionServiceExample {

    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            int sleepSeconds = new Random().nextInt(10);
            String msg = String.format("sleep %d s", sleepSeconds);
            System.out.println(msg);
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return Thread.currentThread().getName() + " " + msg;
        }
    }

    public static void main(String[] args) {
        try {
            normalExecutorService();
            completionExecutorService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void normalExecutorService() throws InterruptedException, TimeoutException, ExecutionException {
        int numThread = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < numThread; i++) {
            Future<String> future = executor.submit(new Task());
            futureList.add(future);
        }
        while (numThread > 0) {
            for (Iterator<Future<String>> it = futureList.iterator();it.hasNext();) {
                Future<String> future = it.next();
                String result = future.get(20, TimeUnit.SECONDS);
                if (null != result) {
                    it.remove();
                    numThread--;
                    System.out.println(result);
                }
            }
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            TimeUnit.SECONDS.sleep(3);
        }

    }

    private static void completionExecutorService() throws InterruptedException, ExecutionException {
        int numThread = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(numThread);

        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < numThread; i++) {
            completionService.submit(new Task());
        }

        for (int i = 0; i < numThread; i++) {
            Future<String> future = completionService.take();
            System.out.println(future.get());
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
