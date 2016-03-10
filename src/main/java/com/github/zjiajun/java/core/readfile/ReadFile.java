package com.github.zjiajun.java.core.readfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhujiajun
 * 16/1/26 21:30
 */
public class ReadFile {

    private static final String FILE_NAME = "/Users/zhujiajun/Work/ideaProjects/java-core/src/main/resources/lines.txt";

    public static void main(String[] args) {
        System.out.println("Stream");
        stream();
        System.out.println("Stream Extra");
        streamExtra();
        System.out.println("BufferedReaderStream");
        bufferedReaderStream();
        System.out.println("Classic BufferedReader");
        classicBufferedReader();
        System.out.println("Scanner");
        scanner();
    }


    private static void stream() {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void streamExtra() {
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
            list = stream.filter(line -> !line.startsWith("line3")).map(String::toUpperCase).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
    }

    private static void bufferedReaderStream() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            Stream<String> lines = br.lines();
            list = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
    }

    private static void classicBufferedReader() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanner() {
        try (Scanner scanner = new Scanner(new File((FILE_NAME)))) {
            while (scanner.hasNext())
                System.out.println(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}