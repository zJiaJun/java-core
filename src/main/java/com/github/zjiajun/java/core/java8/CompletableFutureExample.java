package com.github.zjiajun.java.core.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhujiajun
 * 16/6/21 16:17
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(()-> System.out.println(1));
        CompletableFuture<Double> completableFuture =
                CompletableFuture.supplyAsync(() -> "123").thenApply(Integer::parseInt).thenApply(r -> r * r * Math.PI);
        System.out.println(completableFuture.get());

    }

}
