package com.github.zjiajun.java.core.serializable;

import java.util.Date;

/**
 * Created by zhujiajun
 * 16/2/6 20:27
 *
 * 1.敏感字段加密序列化
 *
 * 2.静态变量不会被序列化
 *   序列化保存的是对象的状态,静态变量属于类的状态,因此序列化并不保存静态变量
 */

public class SerializableDemo {

    public static void main(String[] args) {
        SerializableObj obj = new SerializableObj();//staticVar = 5
        obj.setName("github");
        obj.setAge(30);
        obj.setBrithday(new Date());
        obj.setGender("male");
        obj.setPassword("123");
        System.out.println(obj);

        SerializableObj.staticVar = 10;

        String fileName = "temp";
        SerializationUtil.serialize(obj,fileName);
        SerializableObj serializableObj = (SerializableObj) SerializationUtil.deserialize(fileName);
        System.out.println("解密后的密码: " + serializableObj.getPassword());

        System.out.println("通过反序列化后的静态值: " + SerializableObj.staticVar);

        System.out.println(obj == serializableObj);
    }
}
