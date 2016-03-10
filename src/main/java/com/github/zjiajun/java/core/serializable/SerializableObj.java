package com.github.zjiajun.java.core.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhujiajun
 * 16/2/6 20:27
 */
public class SerializableObj implements Serializable {

    private static final long serialVersionUID = -3898698004537572316L;

    public static int staticVar = 5;

    private String name;
    private Integer age;
    private Date brithday;
    private transient String gender;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private void writeObject(ObjectOutputStream oos) throws IOException, IllegalAccessException {
        ObjectOutputStream.PutField putField = oos.putFields();
        System.out.println("原密码:" + password);
        password = "encryption";//模拟加密
        putField.put("password", password);
        System.out.println("加密后的密码" + password);
        oos.writeFields();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException, IllegalAccessException {
        ObjectInputStream.GetField getField = ois.readFields();
        Object object = getField.get("password", "");
        System.out.println("要解密的字符串:" + object.toString());
        password = "123";//模拟解密,需要获得本地的密钥
    }

    @Override
    public String toString() {
        return "SerializableObj{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", brithday=" + brithday +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
