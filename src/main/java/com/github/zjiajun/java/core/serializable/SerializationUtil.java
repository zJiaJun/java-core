package com.github.zjiajun.java.core.serializable;

import java.io.*;

/**
 * Created by zhujiajun
 * 16/2/7 19:50
 */
public class SerializationUtil {

    public static Object deserialize(String fileName) {
        File file = new File(fileName);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (file.exists()) file.delete();
        }
        throw new RuntimeException();
    }

    public static void serialize(Object object,String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
