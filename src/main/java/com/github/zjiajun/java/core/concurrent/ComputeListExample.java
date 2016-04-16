package com.github.zjiajun.java.core.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhujiajun
 * 16/4/12 11:54
 *
 * 初始化N条数据,格式为List<String> string_n
 * 分区List, 提交线程池执行,转换为n_string
 * 添加到最终List中
 *
 */
public class ComputeListExample {

    private static final Integer CORE_POOL_SIZE = 10;
    private static final Integer PARTITION_SIZE = 10;

    private final ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(CORE_POOL_SIZE,CORE_POOL_SIZE, 0, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(100));

    private static final int initDateSize = 1000_000;

    private List<String> stringList;
    private List<List<String>> partitionList;

    private final List<String> resultList = new ArrayList<>();

    private final CyclicBarrier cb = new CyclicBarrier(PARTITION_SIZE, new Callback() {
        @Override
        void callBackResult() {
            cdl.countDown();
        }
    });
    private final CountDownLatch cdl = new CountDownLatch(1);


    public ComputeListExample await() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<String> result() {
        shutdown();
        return this.resultList;
    }

    abstract class Callback implements Runnable {

        @Override
        public void run() {
            callBackResult();
        }

        abstract void callBackResult();
    }



    public ComputeListExample() {
        initDate();
    }

    private void initDate() {
        this.stringList = new ArrayList<>();
        for (int i = 0; i < initDateSize; i++) {
            stringList.add("string_" + i);
        }
    }

    public ComputeListExample partitionList() {
        int size = stringList.size();
        partitionList =  new ArrayList<>();
        for (int i = 0; i < PARTITION_SIZE; i++) {
            partitionList.add(
                    new ArrayList<>(stringList.subList(size / PARTITION_SIZE * i,(size / PARTITION_SIZE) * (i + 1))));
        }
        return this;
    }

    public ComputeListExample executeTask() {
        if (partitionList == null || partitionList.isEmpty())
            throw new IllegalArgumentException("partitionList is null");
        for (List<String> partition : partitionList) {
            threadPoolExecutor.execute(new ListTask(partition));
        }
        return this;
    }

    private void shutdown() {
        threadPoolExecutor.shutdown();
    }

    class ListTask implements Runnable {

        private List<String> partition;

        public ListTask(List<String> partition) {
            this.partition = partition;
        }

        @Override
        public void run() {
            if (partitionList == null || partitionList.isEmpty()) return;
            for (String str : partition) {
                String[] split = str.split("_");
                String s = split[1] + "_" + split[0];
                synchronized (resultList) {
                    resultList.add(s);
                }
            }
            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ComputeListExample example = new ComputeListExample();
        List<String> result = example.partitionList().executeTask().await().result();
        System.out.println(result.size());

    }
}
