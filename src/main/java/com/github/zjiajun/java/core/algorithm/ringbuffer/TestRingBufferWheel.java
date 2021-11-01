package com.github.zjiajun.java.core.algorithm.ringbuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/11/1 19:26
 */
public class TestRingBufferWheel {

    private static Logger logger = LoggerFactory.getLogger(TestRingBufferWheel.class) ;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        RingBufferWheel wheel = new RingBufferWheel(executorService);
        while (true) {
            logger.info("task size={}, task map size={}", wheel.taskSize(), wheel.taskMapSize());
            TimeUnit.SECONDS.sleep(1);

            for (int i = 0; i < 1000; i++) {
                RingBufferWheel.Task task = new ByteTask(1024 * 1024);
                task.setKey(1);
                wheel.addTask(task);
            }
        }
    }

    private static class ByteTask extends RingBufferWheel.Task {

        private byte[] b;

        public ByteTask(int size) {
            this.b = new byte[size];
        }

        @Override
        public void run() {
        }
    }
}
