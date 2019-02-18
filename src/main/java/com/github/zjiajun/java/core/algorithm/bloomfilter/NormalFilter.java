package com.github.zjiajun.java.core.algorithm.bloomfilter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2019/2/18 21:39
 */
public class NormalFilter implements HugeFilter {

    private final Set<Integer> integerHashSet;

    public NormalFilter(Integer size) {
        integerHashSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            integerHashSet.add(i);
        }
    }

    /**
     * -Xms64 -Xms64m -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError
     * @param num
     */
    @Override
    public boolean contains(Integer num) {
        long start = System.currentTimeMillis();

        boolean contains = integerHashSet.contains(num);

        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
        return contains;
    }



}
