package com.github.zjiajun.java.core.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhujiajun
 * 16/1/8 20:43
 *
 * Condition例子
 */
public class LockConditionExample<T> {

    private final Object [] items;

    private final ReentrantLock lock;

    private Condition notFull;
    private Condition notEmpty;

    private int head;
    private int tail;
    private int count;

    public LockConditionExample() {
        this(5);
    }

    public LockConditionExample(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        items = new Object[capacity];
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    /**
     * 往数组尾部添加数据
     * 如实际count等于数组长度,即队列满了会挂起,等待notFull的信号
     * @param t value
     * @throws InterruptedException
     */
    public void put(T t) throws InterruptedException {
       lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[tail] = t;
            if (++tail == items.length)
                //tail尾部自增,下次put的时候items[自增的值];如自增后等于数组长度,说明已经队列已经满了,此时tail重0计算
                tail = 0;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 从数组头部取数据
     * 如count=0,即队列为空会挂起,等待notEmpty信号
     * @return value
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            @SuppressWarnings("unchecked")
            T t = (T) items[head];
            items[head] = null; //gc work
            if (++head == items.length)
                head = 0;
            count--;
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockConditionExample<Integer> example = new LockConditionExample<>();
        for (int i = 0;i < 5;i++) {
            example.put(i);
        }
        for (int i = 0;i < 5;i++) {
            Integer value = example.take();
        }
    }
}
