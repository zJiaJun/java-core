package com.github.zjiajun.java.core.concurrent.theradpool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/4/29 09:19
 *
 * 使用forkjoin计算给定目录下的符合扩展名的文件
 */
public class ForkJoinExample {

    private static class FolderTask extends RecursiveTask<List<String>> {

        private static final long serialVersionUID = 6266526949138955316L;

        private String path;        //查找的文件夹路径
        private String extension;   //查找的文件扩展名

        public FolderTask(String path, String extension) {
            this.path = path;
            this.extension = extension;
        }

        @Override
        protected List<String> compute() {
            List<String> list = new ArrayList<>();        //存储文件夹中的文件
            List<FolderTask> tasks = new ArrayList<>();   //用来保存将要处理存储在文件夹内的子文件夹的子任务
            File file = new File(path);
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        FolderTask folderTask = new FolderTask(f.getAbsolutePath(),extension);
                        folderTask.fork();
                        tasks.add(folderTask);
                    } else {
                        if (f.getName().endsWith(extension)) {
                            list.add(f.getAbsolutePath());
                        }
                    }
                }
                if (tasks.size() > 50) {
                    System.out.printf("%s: %d tasks ran.\n",file.getAbsolutePath(),tasks.size());
                }

                for (FolderTask task : tasks) {
                    list.addAll(task.join());
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FolderTask folderTask = new FolderTask("/Users/zhujiajun/Work","log");
        pool.execute(folderTask);

        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
            System.out.printf("*****************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(!folderTask.isDone());

        pool.shutdown();
        List<String> join = folderTask.join();
        join.forEach(System.out::println);
    }
}
