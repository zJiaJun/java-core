package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhujiajun
 * 16/4/29 09:19
 */
public class ForkJoinExample {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();


    private class Task extends RecursiveTask<String> {

        @Override
        protected String compute() {
            return null;
        }
    }

}
