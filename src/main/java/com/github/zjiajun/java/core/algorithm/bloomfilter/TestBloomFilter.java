package com.github.zjiajun.java.core.algorithm.bloomfilter;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2019/2/18 21:49
 */
public class TestBloomFilter {

    public static void main(String[] args) {
//        HugeFilter filter = new NormalFilter(HugeFilter.SIZE_OF_1KW);
//        System.out.println(filter.contains(876));

        HugeFilter filter = new SimpleBloomFilter(HugeFilter.SIZE_OF_1KW, HugeFilter.SIZE_OF_1KW);
        System.out.println(filter.contains(19427261));

    }
}
