package com.github.zjiajun.java.core.concurrent.theradpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhujiajun
 * @since 2017/7/19
 */
public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(10);

        List<Future<String>> futureList = new ArrayList<>();
        Future<String> submit20 = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(20);
                return "sleep 20 s";

            }
        });

        Future<String> submit5 = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "sleep 5 s";
            }
        });
        futureList.add(submit20);
        futureList.add(submit5);//5s 即使完成了，还要等待20s的任务完成才返回,可以使用CompletionService



        for (Future<String> stringFuture : futureList) {
            String s = stringFuture.get();
            System.out.println(s);
        }

        service.shutdown();


    }
}
