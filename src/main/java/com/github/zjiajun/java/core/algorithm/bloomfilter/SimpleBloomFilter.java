package com.github.zjiajun.java.core.algorithm.bloomfilter;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2019/2/18 21:56
 */
public class SimpleBloomFilter implements HugeFilter {

    private final int [] array;

    private final int arraySize;

    public SimpleBloomFilter(Integer size, Integer arraySize) {
        this.arraySize = arraySize;
        this.array = new int[arraySize];
        for (int i = 0; i < size; i++) {
            put(i);
        }
    }

    private void put(int num) {
        int hashCode1 = hashCode1(num + "");
        int hashCode2 = hashCode2(num + "");
        int hashCode3 = hashCode3(num + "");

        array[hashCode1 % arraySize] = 1;
        array[hashCode2 % arraySize] = 1;
        array[hashCode3 % arraySize] = 1;
    }

    @Override
    public boolean contains(Integer num) {
        long start = System.currentTimeMillis();

        int hashCode1 = hashCode1(num + "");
        int hashCode2 = hashCode2(num + "");
        int hashCode3 = hashCode3(num + "");

        if (array[hashCode1 % arraySize] == 0) {
            return false;
        }
        if (array[hashCode2 % arraySize] == 0) {
            return false;
        }
        if (array[hashCode3 % arraySize] == 0) {
            return false;
        }

        long end = System.currentTimeMillis();
//        System.out.println("耗时: " + (end - start));
        return true;
    }

    private int hashCode1(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    private int hashCode2(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    private int hashCode3(String key) {
        int hash,i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }
}
