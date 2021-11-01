package com.github.zjiajun.java.core.algorithm.ringbuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/11/1 17:27
 */
public class RingBufferWheel {

    private final static Logger logger = LoggerFactory.getLogger(RingBufferWheel.class);

    private static final int STATIC_RING_SIZE = 64;

    private Object[] ringBuffer;

    private int bufferSize;

    private ExecutorService executorService;

    private volatile int size = 0;

    private volatile boolean stop = false;

    private volatile AtomicBoolean start = new AtomicBoolean(false);

    private AtomicInteger tick = new AtomicInteger();

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private AtomicInteger taskId = new AtomicInteger();
    private Map<Integer, Task> taskMap = new ConcurrentHashMap<>();

    public RingBufferWheel(ExecutorService executorService) {
        this.executorService = executorService;
        this.bufferSize = STATIC_RING_SIZE;
        this.ringBuffer = new Object[bufferSize];
    }

    public RingBufferWheel(ExecutorService executorService, int bufferSize) {
        this(executorService);
        if (!powerOf2(bufferSize)) {
            throw new RuntimeException("bufferSize=[" + bufferSize + "] must be a power of 2");
        }
        this.bufferSize = bufferSize;
        this.ringBuffer = new Object[bufferSize];
    }

    public int addTask(Task task) {
        int key = task.getKey();
        int id;
        try {
            lock.lock();
            int index = mod(key, bufferSize);
            task.setIndex(index);
            Set<Task> tasks = get(index);
            int cycleNum = cycleNum(key, bufferSize);
            if (tasks != null) {
                task.setCycleNum(cycleNum);
                tasks.add(task);
            } else {
                task.setIndex(index);
                task.setCycleNum(cycleNum);
                Set<Task> sets = new HashSet<>();
                sets.add(task);
                put(key, sets);
            }
            id = taskId.incrementAndGet();
            task.setTaskId(id);
            taskMap.put(id, task);
            size++;
        } finally {
            lock.unlock();
        }
        start();
        return id;
    }

    public boolean cancel(int id) {
        boolean flag = false;
        Set<Task> tempTask = new HashSet<>();
        try {
            lock.lock();
            Task task = taskMap.get(id);
            if (task == null) {
                return false;
            }
            Set<Task> tasks = get(task.getIndex());
            for (Task tk : tasks) {
                if (tk.getKey() == task.getKey() && tk.getCycleNum() == task.getCycleNum()) {
                    size--;
                    flag = true;
                    taskMap.remove(id);
                } else {
                    tempTask.add(tk);
                }
            }
            ringBuffer[task.getIndex()] = tempTask;
        } finally {
            lock.unlock();
        }
        return flag;
    }

    public void start() {
        if (!start.get()) {
            if (start.compareAndSet(start.get(), true)) {
                logger.info("Delay task is starting");
                Thread job = new Thread(new TriggerJob());
                job.setName("consumer RingBuffer thread");
                job.start();
                start.set(true);
            }
        }
    }

    public void stop(boolean force) {
        if (force) {
            logger.info("Delay task is forced stop");
            stop = true;
            executorService.shutdownNow();
        } else {
            logger.info("Delay task is stopping");
            if (taskSize() > 0) {
                try {
                    lock.lock();
                    condition.await();
                    stop = true;
                } catch (InterruptedException e) {
                    logger.error("InterruptedException", e);
                } finally {
                    lock.unlock();
                }
            }
            executorService.shutdown();
        }
    }

    public int taskSize() {
        return size;
    }

    public int taskMapSize() {
        return taskMap.size();
    }

    private Set<Task> get(int index) {
        return (Set<Task>) ringBuffer[index];
    }

    private void put(int key, Set<Task> tasks) {
        int index = mod(key, bufferSize);
        ringBuffer[index] = tasks;
    }

    private Set<Task> remove(int key) {
        Set<Task> tempTask = new HashSet<>();
        Set<Task> result = new HashSet<>();

        Set<Task> tasks = (Set<Task>) ringBuffer[key];
        if (tasks == null) {
            return result;
        }
        for (Task task : tasks) {
            if (task.getCycleNum() == 0) {
                result.add(task);
                size2Notify();
            } else {
                task.setCycleNum(task.getCycleNum() - 1);
                tempTask.add(task);
            }
            taskMap.remove(task.getTaskId());
        }
        ringBuffer[key] = tempTask;
        return result;
    }

    private void size2Notify() {
        try {
            lock.lock();
            size--;
            if (size == 0) {
                condition.signal();
            }
        } finally {
            lock.unlock();;
        }
    }

    private int mod(int target, int mod) {
        // target % mod
        target = target + tick.get();
        return target & (mod - 1);
    }

    private int cycleNum(int target, int mod) {
        //target / mod
        return target >> Integer.bitCount(mod - 1);
    }

    private boolean powerOf2(int target) {
        if (target < 0) {
            return false;
        }
        int value = target & (target -1);
        if (value != 0) {
            return false;
        }
        return true;
    }

    public abstract static class Task extends Thread {

        private int index;

        private int cycleNum;

        private int key;

        private int taskId;

        @Override
        public void run() {

        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getCycleNum() {
            return cycleNum;
        }

        public void setCycleNum(int cycleNum) {
            this.cycleNum = cycleNum;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }
    }

    private class TriggerJob implements Runnable {

        @Override
        public void run() {
            int index = 0;
            while (!stop) {
                try {
                    Set<Task> tasks = remove(index);
                    for (Task task : tasks) {
                        executorService.submit(task);
                    }

                    if (++index > bufferSize - 1) {
                        index = 0;
                    }

                    //Total tick number of records
                    tick.incrementAndGet();
                    TimeUnit.SECONDS.sleep(1);

                } catch (Exception e) {
                    logger.error("Exception", e);
                }
            }
            logger.info("Delay task has stopped");
        }
    }
}
