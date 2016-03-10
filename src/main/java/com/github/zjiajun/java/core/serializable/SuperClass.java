package com.github.zjiajun.java.core.serializable;

/**
 * Created by zhujiajun
 * 16/2/7 19:45
 */
public class SuperClass {

    private int id;
    private String version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public SuperClass() {}

    @Override
    public String toString() {
        return "SuperSer{" +
                "id=" + id +
                ", version='" + version + '\'' +
                '}';
    }
}
