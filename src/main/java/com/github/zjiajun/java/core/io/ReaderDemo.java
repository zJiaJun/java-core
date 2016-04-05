package com.github.zjiajun.java.core.io;

import java.io.*;

/**
 * Created by zhujiajun
 * 16/4/5 21:51
 */
public class ReaderDemo {

    public static void main(String[] args) throws IOException {
        Reader reader = new InputStreamReader(System.in);
        System.out.println(((char) reader.read()));
        System.out.println("---------------------");

        reader = new FileReader("src/main/resources/lines.txt");
        System.out.println((char)reader.read());
        System.out.println("---------------------");

        char [] c = new char[]{'a','b'};
        reader = new CharArrayReader(c);
        c[0] = 'A';
        System.out.println((char) reader.read());
        System.out.println("---------------------");

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/lines.txt"), 10 * 1024);//缓冲区10k
        System.out.println(br.readLine());
        System.out.println("---------------------");

        LineNumberReader lr = new LineNumberReader(new FileReader("src/main/resources/lines.txt"), 10 * 1024);
        System.out.println(lr.getLineNumber());
        System.out.println(lr.readLine());
        System.out.println(lr.getLineNumber());
        System.out.println(lr.readLine());

        System.out.println("---------------------");
        System.out.println("---------------------");

        reader.close();


    }
}
