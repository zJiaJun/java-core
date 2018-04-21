package com.github.zjiajun.java.core.algorithm.consistent;

/**
 * Created by zhujiajun
 * 15/12/29 20:36
 *
 * 一致性hash算法-服务器节点
 */
public class Node {

    private String name;
    private String ip;
//    private String mac;

    public Node(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return name + ":"+ ip;
    }

}
