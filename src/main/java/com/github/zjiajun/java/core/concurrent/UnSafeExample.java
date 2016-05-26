package com.github.zjiajun.java.core.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhujiajun
 * 16/5/26 09:30
 *
 * 反射构建Unsafe对象,调用cas操作,无锁的,由cpu底层cmpxchg完成原子操作,乐观锁方式(失败,重试)
 * synchronized 悲观锁 互斥
 */
public class UnSafeExample {


    private volatile int value;
    private static Unsafe unsafe;
    private static long valueOffset;//内存偏移量

    static {
        try {
            Class<Unsafe> unsafeClass = Unsafe.class;
            Constructor<Unsafe> declaredConstructor = unsafeClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            unsafe = declaredConstructor.newInstance();
            //获得字段在对象中的偏移量
            valueOffset = unsafe.objectFieldOffset(UnSafeExample.class.getDeclaredField("value"));
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void cas() {
        int intVolatile = unsafe.getIntVolatile(this, valueOffset);
        unsafe.compareAndSwapInt(this,valueOffset,intVolatile,2);
    }


    public static void main(String[] args) {
        UnSafeExample example = new UnSafeExample();
        System.out.printf("初始值: %d \n",example.value);
        example.cas();
        System.out.printf("修改值: %d \n", example.value);

    }
}
