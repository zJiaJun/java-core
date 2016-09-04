package com.github.zjiajun.java.core.datastructure;

/**
 * @author zhujiajun
 * @since 16/9/4
 *
 * 最简单的打印1到N的正整数
 * 1. for循环
 * 2. 递归实现
 *
 * 解决问题方法的效率,跟空间的利用效率有关
 *
 * 递归实现,递归次数太多,就会导致栈溢出java.lang.StackOverflowError,如递归10万次
 * 此时就需要配置Xss jvm选项,指定栈大小,既空间
 */
public class PrintN {

    static void forPrintNum(int n) {
        for (int i = 1;i <= n; i++) {
            System.out.println(i);
        }
    }

    static void recursionPrintNum(int n) {
        if (n != 0) { // n > 0
            recursionPrintNum(n - 1);
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        forPrintNum(100_00);
        long t2 = System.currentTimeMillis();
        System.out.println("for time " + (t2 - t1));

        long t3 = System.currentTimeMillis();
        recursionPrintNum(100_00);
        long t4 = System.currentTimeMillis();
        System.out.println("recursion time " + (t4 - t3));

    }
}
