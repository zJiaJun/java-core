package com.github.zjiajun.java.core.algorithm.sort;

/**
 * @author zhujiajun
 * @since 2016/12/12
 */
public final class ArrayGen {

    public static int [] random(int size) {
        int [] array = new int[size];
        for (int i = 0;i < size; i++)
            array[i] = (int) (Math.random() * 100);
        return array;
    }
}
