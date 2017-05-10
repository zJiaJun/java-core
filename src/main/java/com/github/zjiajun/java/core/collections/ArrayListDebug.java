package com.github.zjiajun.java.core.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiajun
 * @since 2016/12/12
 */
public class ArrayListDebug {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        list.remove(0);
    }
}
