package com.github.zjiajun.java.core.serializable;

import java.io.*;

/**
 * Created by zhujiajun
 * 16/2/8 21:11
 *
 * 序列化规则
 * 对同一个对象两次写入文件.序列化机制为了节省磁盘空间,当写入文件为同一对象时,
 * 不会再将对象的内容进行存储,而只好存储对象的引用.反序列化时,恢复引用关系
 */
public class SerializableRuleDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Test test = new Test("test");
        File file = new File("test.ser");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        test.setName("test01");
        oos.writeObject(test);
        oos.flush();
        test.setName("test02");
        System.out.println("first: " + file.length());//123 byte
        oos.writeObject(test);
        oos.close();
        System.out.println("second: " + file.length());//128 byte
        //第2次只比第1次多了5字节,并不会文件大小的2倍,5字节de存储空间就是新增的对象引用和一些控制信息

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Test test1 = (Test) ois.readObject();
        Test test2 = (Test) ois.readObject();
        ois.close();

        System.out.println(test1 == test2);// true
        System.out.println(test1);
        System.out.println(test2);

        if (file.exists()) file.delete();
    }

    private static class Test implements Serializable {

        private static final long serialVersionUID = -8205537138912363568L;
        private String name;

        public Test(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
