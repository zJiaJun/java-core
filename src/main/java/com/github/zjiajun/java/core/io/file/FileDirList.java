package com.github.zjiajun.java.core.io.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujiajun
 * 16/3/20 19:38
 */
public class FileDirList {

    public static void main(String[] args) {
        File file = new File(".");
        String[] list = file.list();
        for (String s : list)
            System.out.println(s);

        File [] jlist = file.listFiles((dir, name) -> name.equals("pom.xml"));

        for (File s : jlist) {
            System.out.println(s);
        }

        Store dirsAndFiles = getDirsAndFiles(".");
        dirsAndFiles.dirs.forEach(System.out::println);
        System.out.println("------------------------");
        dirsAndFiles.files.forEach(System.out::println);
    }


    private static Store getDirsAndFiles(String filePath) {
        return getDirsAndFiles(new File(filePath));
    }

    private static Store getDirsAndFiles(File files) {
        Store storge = new Store();
        for (File file : files.listFiles()) {
            if (file.isDirectory()) {
                storge.dirs.add(file);
                storge.addAll(getDirsAndFiles(file));
            } else {
                storge.files.add(file);
            }
        }
        return storge;
    }

    private static class Store {
        List<File> files;
        List<File> dirs;

        public Store() {
            this.files = new ArrayList<>();
            this.dirs = new ArrayList<>();
        }
        public void addAll(Store other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }
    }
}
