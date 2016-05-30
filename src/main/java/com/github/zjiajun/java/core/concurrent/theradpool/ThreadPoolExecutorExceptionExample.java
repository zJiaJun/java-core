package com.github.zjiajun.java.core.concurrent.theradpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhujiajun
 * 16/5/30 21:25
 */
public class ThreadPoolExecutorExceptionExample {

    final ExecutorService executorService = new ThreadPoolExecutor(5,5,0, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10));

    public void executeWork(int num) {
        for (int i = 0; i < num; i++) {
            executorService.execute(() -> {
                System.out.println("Begin executeWork throw exception");
                System.out.println(1 / 0);// throw ArithmeticException /by zero
            });
        }
    }

    public void submitWorkNoThrowException(int num) {
        for (int i = 0; i < num; i++) {
            executorService.submit(() -> {
                System.out.println("Begin submitWork throw exception");
                System.out.println(1 / 0);//no throw ArithmeticException /by zero
            });
        }
    } //应该使用try catch包裹起来,或者使用Future.get重新抛异常

    public List<Future<String>> submitWork(int num) {
        List<Future<String>> result = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            result.add(executorService.submit(() -> {
                System.out.println("Begin submitWork throw exception");
                System.out.println(1/0);
                return "123";
            }));
        }
        return result;
    }


    public void stop() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutorExceptionExample exceptionExample = new ThreadPoolExecutorExceptionExample();
        exceptionExample.executeWork(5);
        exceptionExample.submitWorkNoThrowException(5);
        List<Future<String>> futures = exceptionExample.submitWork(5);

        for (Future<String> f : futures) {
            try {
                String s = f.get();
                System.out.println(s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        exceptionExample.stop();
    }
}
