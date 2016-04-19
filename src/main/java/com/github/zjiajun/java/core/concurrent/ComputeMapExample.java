package com.github.zjiajun.java.core.concurrent;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by zhujiajun
 * 16/4/18 21:49
 */
public class ComputeMapExample {


    private static List<String> list = new ArrayList<>();
    private List<List<String>> lists = new ArrayList<>();

    static {
        list.add("imp:00:tom:10");
        list.add("imp:00:tom:20");
        list.add("clk:02:leon:30");
        list.add("clk:02:leon:40");

        list.add("imp:00:tom:15");
        list.add("imp:00:tom:25");
        list.add("clk:02:leon:35");
        list.add("clk:02:leon:45");
    }

    private final ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(100));

    private ConcurrentHashMap<String,MapValue> resultMap = new ConcurrentHashMap<>();
    private CountDownLatch cdl = new CountDownLatch(2);

    class MapValue {
        private String name;
        private long imp;
        private long clk;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getImp() {
            return imp;
        }

        public void setImp(long imp) {
            this.imp = imp;
        }

        public long getClk() {
            return clk;
        }

        public void setClk(long clk) {
            this.clk = clk;
        }

        @Override
        public String toString() {
            return "MapValue{" +
                    "name='" + name + '\'' +
                    ", imp=" + imp +
                    ", clk=" + clk +
                    '}';
        }
    }


    public ComputeMapExample() {
        lists.add(new ArrayList<>(list.subList(0,4)));
        lists.add(new ArrayList<>(list.subList(4,list.size())));
    }

    public void executeTask(List<List<String>> datas) {
        for (final List<String> data : datas) {
            ConcurrentHashMap<String,MapValue> map = new ConcurrentHashMap<>();
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (String line : data) {
                        String[] split = line.split(":");
                        int hour = Integer.parseInt(split[1]) == 0 ? 24 : Integer.parseInt(split[1]);
                        String key = split[2] + "_" + hour;
                        MapValue value = map.get(key);
                        if (value == null) {
                            value = new MapValue();
                        }
                        value.setName(split[2]);
                        if ("imp".equals(split[0])) {
                            value.setImp(value.getImp() + Long.parseLong(split[3]));
                        } else if ("clk".equals(split[0])) {
                            value.setClk(value.getClk() + Long.parseLong(split[3]));
                        }
                        map.put(key,value);
                    }

                    /* this is problem;这里2个线程,会覆盖resultMap中的数据 */
//                    resultMap.putAll(resultMap);

                    lockMap(map);
                    cdl.countDown();
                }
            });
        }
    }

    public synchronized void lockMap(ConcurrentHashMap<String, MapValue> map) {
        for (Map.Entry<String, MapValue> next : map.entrySet()) {
            MapValue oldValue = resultMap.get(next.getKey());
            if (oldValue == null) {
                oldValue = new MapValue();
            }
            oldValue.setImp(oldValue.getImp() + next.getValue().getImp());
            oldValue.setClk(oldValue.getClk() + next.getValue().getClk());
            resultMap.put(next.getKey(), oldValue);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ComputeMapExample mapExample = new ComputeMapExample();
        mapExample.executeTask(mapExample.lists);

        mapExample.cdl.await();

        for (Map.Entry<String, MapValue> entry1 : mapExample.resultMap.entrySet()) {
            System.out.println("entry1:" + entry1);
        }
        mapExample.threadPoolExecutor.shutdown();

    }
}
