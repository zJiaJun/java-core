package com.github.zjiajun.java.core.concurrent;

import java.util.Vector;

/**
 * @author zhujiajun
 * @since 2016/12/17
 */
public class VectorErrorExample {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                vector.add(j);
            }

            new Thread(() -> {
                for (int j = 0; j < vector.size(); j++) {
                    vector.remove(j);
                }
            }).start();

            new Thread(() -> {
                for (int j = 0; j < vector.size(); j++) {
                    System.out.println(vector.get(j));
                }
            }).start();
        }

    }
}
