package com.github.zjiajun.java.core.serializable;

import java.io.*;

/**
 * Created by zhujiajun
 * 16/2/7 20:10
 */
public class SubClass extends SuperClass implements Serializable,ObjectInputValidation {

    private static final long serialVersionUID = -3719872492704119933L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubClass{" +
                "name='" + name + '\'' +
                '}' + super.toString();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(getId());
        oos.writeObject(getVersion());

    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        setId(ois.readInt());
        setVersion((String) ois.readObject());
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        System.out.println("validateObject...");
        if (getId() <= 0 ) throw new InvalidObjectException("Id is wrong");
        if (getVersion() == null || "".equals(getVersion())) throw new InvalidObjectException("Version is wrong");
    }
}
