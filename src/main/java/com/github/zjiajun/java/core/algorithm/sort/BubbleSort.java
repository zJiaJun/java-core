package com.github.zjiajun.java.core.algorithm.sort;

import java.util.Arrays;

/**
 * @author zhujiajun
 * @since 2016/12/12
 *
 * 冒泡排序
 */
public class BubbleSort {

   int [] sort(int [] array) {
       int temp;
       for (int i = 1;i < array.length;i++) {
           for (int j = 0;j < array.length - 1;j++) {
               if (array[j] > array[j+1]) {
                   temp = array[j];
                   array[j] = array[j+1];
                   array[j+1] = temp;
               }
           }
       }
       return array;
   }

    public static void main(String[] args) {
       int [] array = ArrayGen.random(100);
       BubbleSort bubbleSort = new BubbleSort();
       int [] sortArray = bubbleSort.sort(array);
       System.out.println(Arrays.toString(sortArray));
    }
}
