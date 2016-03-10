package com.github.zjiajun.java.core.serializable;

import java.io.InvalidObjectException;

/**
 * Created by zhujiajun
 * 16/2/7 20:15
 *
 * 子类父类序列化相关问题
 *
 * 1.通过自定义子类的writeObject和readObject方法写入父类的值
 * 2.父类直接实现Serializable接口
 * 3.transient关键字使得字段可以不被序列化,还有一种方法时,将不需要得字段抽取到父类中,子类实现Serializable,父类不实现接口
 *   父类的字段将不被序列化
 *
 */
public class SerSuperSubDemo {

    public static void main(String[] args) throws InvalidObjectException {
        SubClass subClass = new SubClass();
        subClass.setId(100);
        subClass.setVersion("001");
        subClass.setName("subName");
        System.out.println(subClass);

        String fileName = "temp";
        SerializationUtil.serialize(subClass,fileName);

        SubClass deserialize = (SubClass) SerializationUtil.deserialize(fileName);
        System.out.println(deserialize);

        subClass.validateObject();
    }
}
