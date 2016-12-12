package com.github.zjiajun.java.core.algorithm.sort;

import java.util.Arrays;

/**
 * @author zhujiajun
 * @since 2016/12/12
 *
 * 快速排序
 */
public class QuickSort {


    int [] sort(int [] array, int low, int high) {
        int i = low;
        int j = high;
        if (low < high) {
            int temp = array[j];
            while (i != j) {
                while (i < j && array[i] <= temp)
                    i += 1;
                array[j] = array[i];
                while (j > i && array[j] >= temp)
                    j -= 1;
                array[i] = array[j];
            }
            array[i] = temp;
            sort(array,low,i-1);
            sort(array,i + 1,high);
        }
        return array;
    }


    public static void main(String[] args) {
        int [] array = ArrayGen.random(10);
        QuickSort quickSort = new QuickSort();
        int [] sortArray = quickSort.sort(array,0,array.length - 1);
        System.out.println(Arrays.toString(sortArray));
    }
}
